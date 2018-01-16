package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento.gdx.factory.TileFactoryFactory;
import net.snowyhollows.bento.gdx.factory.TiledMapFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum TileCollisionManagerFactory implements BentoFactory<TileCollisionManager> {
    IT;

    @Override
    public TileCollisionManager createInContext(Bento bento) {
        return new TileCollisionManager(
                bento.get(TiledMapFactory.IT),
                bento.get(TileFactoryFactory.IT)
        );
    }
}
