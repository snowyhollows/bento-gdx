package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum ColliderFactory implements BentoFactory<LevelWalker.Collider<?>> {
    IT;

    @Override
    public LevelWalker.Collider<?> createInContext(Bento bento) {
        return TileCollisionManagerFactory.IT.createInContext(bento);
    }
}
