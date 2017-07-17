package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento.gdx.GdxFactories;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum TileCollisionManagerFactory implements BentoFactory<TileCollisionManager> {
    IT;

    @Override
    public TileCollisionManager createInContext(Bento bento) {
        return new TileCollisionManager(
                bento.get(GdxFactories.TILED_MAP),
                bento.get("tiles.tile_factory")
        );
    }
}
