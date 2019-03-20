package net.snowyhollows.bento.gdx.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import net.snowyhollows.bento.gdx.factory.TiledMapFactory;
import net.snowyhollows.bento2.annotation.ByFactory;
import net.snowyhollows.bento2.annotation.WithFactory;

import java.util.*;

public class TextureRegionAnimationsProvider {

	private final TiledMap tiledMap;

	@WithFactory
	public TextureRegionAnimationsProvider(@ByFactory(TiledMapFactory.class) TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}

	public TextureRegionAnimation forTileId(int id) {
		TiledMapTile tile = tiledMap.getTileSets().getTile(id);
		return tileToTextureRegionAnimation(tile);
	}

	public Map<String, TextureRegionAnimation> extractAll() {

        HashMap<String, TextureRegionAnimation> result = new HashMap<String, TextureRegionAnimation>();

		for (TiledMapTileSet tiledMapTiles : tiledMap.getTileSets()) {
			for (TiledMapTile tiledMapTile : tiledMapTiles) {
				if (tiledMapTile.getProperties().containsKey("name")) {
					String name = tiledMapTile.getProperties().get("name").toString();
					result.put(name, tileToTextureRegionAnimation(tiledMapTile));

				}
			}
		}

		return result;
	}

	private TextureRegionAnimation tileToTextureRegionAnimation(TiledMapTile tiledMapTile) {
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

			return new TextureRegionAnimation(
							regions.toArray(new TextureRegion[0]),
							grains, tiledMapTile.getProperties());
		} else {
			return new TextureRegionAnimation(
							new TextureRegion[]{tiledMapTile.getTextureRegion()},
							TextureRegionAnimation.ONE_INTERVAL,
							tiledMapTile.getProperties());
		}
	}
}
