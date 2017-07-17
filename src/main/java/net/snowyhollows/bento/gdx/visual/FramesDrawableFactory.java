package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class FramesDrawableFactory {

    public interface TextureRegionProvider {
        TextureRegion forIndex(int i);
    }

    private final TextureRegion[] regions;

    public FramesDrawableFactory(TextureRegionProvider textureRegionProvider, int... frames) {
        regions = new TextureRegion[frames.length];
        for (int i = 0; i < regions.length; i++) {
            final int idx = Math.abs(frames[i]);
            TextureRegion texture = textureRegionProvider.forIndex(idx);
            if (frames[i] > 0) {
                regions[i] = texture;
            } else {
                regions[i] = new TextureRegion(texture);
                regions[i].flip(true, false);
            }
        }
    }

    public static FramesDrawableFactory createFromTiles(final TiledMap tiledMap, int... frames) {
        return new FramesDrawableFactory(new TextureRegionProvider() {
            @Override
            public TextureRegion forIndex(int idx) {
                return tiledMap.getTileSets().getTile(idx).getTextureRegion();
            }
        }, frames);
    }

    public static FramesDrawableFactory createFromTiles(final TextureRegion[][] regions, int... frames) {
        final int cols = regions[0].length;
        return new FramesDrawableFactory(new TextureRegionProvider() {
            @Override
            public TextureRegion forIndex(int idx) {
                return regions[idx / cols][idx % cols];
            }
        }, frames);
    }

    public static FramesDrawableFactory createFromAtlas(final TextureAtlas atlas, final String name, int... frames) {
        return new FramesDrawableFactory(new TextureRegionProvider() {
            @Override
            public TextureRegion forIndex(int idx) {
                return atlas.findRegion(name, idx);
            }
        }, frames);
    }

    public TextureRegion[] getRegions() {
        return regions;
    }

    
    
}
