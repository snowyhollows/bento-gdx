package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum NoCollisionsFactory implements BentoFactory<NoCollisions> {
    IT;
    @Override
    public NoCollisions createInContext(Bento bento) {
        return new NoCollisions();
    }
}
