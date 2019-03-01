package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum TrailFactory implements BentoFactory<Trail> {
    IT;

    @Override
    public Trail createInContext(Bento bento) {
        return new Trail();
    }
}
