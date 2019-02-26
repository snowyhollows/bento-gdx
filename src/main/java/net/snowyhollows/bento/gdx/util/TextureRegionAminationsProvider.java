/*
 * Copyright (c) 2009-2019 Ericsson AB, Sweden. All rights reserved.
 *
 * The Copyright to the computer program(s) herein is the property of Ericsson AB, Sweden.
 * The program(s) may be used  and/or copied with the written permission from Ericsson AB
 * or in accordance with the terms and conditions stipulated in the agreement/contract under
 * which the program(s) have been supplied.
 *
 */
package net.snowyhollows.bento.gdx.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import net.snowyhollows.bento.gdx.factory.TiledMapFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;

import java.util.*;

public class TextureRegionAminationsProvider {

	private final TiledMap tiledMap;
	private final Bento bento;

	@WithFactory
	public TextureRegionAminationsProvider(@ByFactory(TiledMapFactory.class) TiledMap tiledMap, Bento bento) {
		this.tiledMap = tiledMap;
		this.bento = bento;
	}

	public Map<String, TextureRegionAnimation> extractAll() {

        HashMap<String, TextureRegionAnimation> result = new HashMap<String, TextureRegionAnimation>();

		for (TiledMapTileSet tiledMapTiles : tiledMap.getTileSets()) {
			for (TiledMapTile tiledMapTile : tiledMapTiles) {
				if (tiledMapTile.getProperties().containsKey("name")) {
					String name = tiledMapTile.getProperties().get("name").toString();
					if (tiledMapTile instanceof AnimatedTiledMapTile) {
						final AnimatedTiledMapTile tile = (AnimatedTiledMapTile) tiledMapTile;
						final List<TextureRegion> regions = new ArrayList<TextureRegion>();
						StaticTiledMapTile[] tiles = tile.getFrameTiles();
						int[] intervals = tile.getAnimationIntervals();
						int idx = 0;
						float targetTime = 0;
						float currentTIme = 0;
						final float grain = 0.1f;

						do {
							targetTime += (intervals[idx] / 1000f);
							while (currentTIme < targetTime) {
								regions.add(tiles[idx].getTextureRegion());
								currentTIme += grain;
							}
						} while (++idx < tiles.length);
                        final int[] grains = new int[regions.size()];
                        Arrays.fill(grains, (int)(grain * 1000));

                        result.put(name,
                                new TextureRegionAnimation(
                                    regions.toArray(new TextureRegion[0]),
                                    grains
                                ));
					}
				}
			}
		}

		return result;
	}


}
