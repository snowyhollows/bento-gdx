package net.snowyhollows.bento.gdx.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class RegionsFromTilesCreator {

    private final TiledMap tiledMap;

    public RegionsFromTilesCreator(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }

    public TextureRegion[] create(int... frames) {
        return createMutliTiled(1, 1, frames);
    }

    public TextureRegion[] createMutliTiled(float rows, float cols, int... frames) {
        TextureRegion[] textures = new TextureRegion[frames.length];
        for (int i = 0; i < textures.length; i++) {
            final int idx = Math.abs(frames[i]);
            TextureRegion texture = tiledMap.getTileSets().getTile(idx).getTextureRegion();
            if (rows != 1 || cols != 1) {
                texture = new TextureRegion(texture);
                texture.setRegionWidth((int) (texture.getRegionWidth() * cols));
                texture.setRegionHeight((int) (texture.getRegionHeight() * rows));
            }
            if (frames[i] > 0) {
                textures[i] = texture;
            } else {
                textures[i] = new TextureRegion(texture);
                textures[i].flip(true, false);
            }
        }
        return textures;
    }
}
