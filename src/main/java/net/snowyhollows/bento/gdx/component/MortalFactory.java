package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum MortalFactory implements BentoFactory<Mortal> {
    IT;
    @Override
    public Mortal createInContext(Bento bento) {
        return new Mortal();
    }
}
