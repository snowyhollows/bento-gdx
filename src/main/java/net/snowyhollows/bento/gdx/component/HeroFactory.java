package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum HeroFactory implements BentoFactory<Hero> {
    IT;
    @Override
    public Hero createInContext(Bento bento) {
        return new Hero();
    }
}
