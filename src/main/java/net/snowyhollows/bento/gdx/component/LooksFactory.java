package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum LooksFactory implements BentoFactory<Looks> {
    IT;

    @Override
    public Looks createInContext(Bento bento) {
        return new Looks(
            bento.getString("looks.name"),
            bento
        );
    }
}
