package net.snowyhollows.bento.gdx.factory;

import net.snowyhollows.bento.gdx.util.TileCollisionManager;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum TileCollisionManagerFactory implements BentoFactory<net.snowyhollows.bento.gdx.util.TileCollisionManager> {
    IT;

    @Override
    public TileCollisionManager createInContext(Bento bento) {
        return new TileCollisionManager(
                bento.get(TiledMapFactory.IT),
                bento.get(TileFactoryFactory.IT)
        );
    }
}