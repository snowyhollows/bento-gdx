package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum NoGravityFactory implements BentoFactory<NoGravity> {
    IT;
    @Override
    public NoGravity createInContext(Bento bento) {
        return new NoGravity();
    }
}
