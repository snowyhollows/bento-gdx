package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum DeadlyFactory implements BentoFactory<Deadly> {
    IT;
    @Override
    public Deadly createInContext(Bento bento) {
        return new Deadly();
    }
}
