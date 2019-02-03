package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 6/27/2017.
 */
public enum NamedFactory implements BentoFactory<Named> {
    IT;

    @Override
    public Named createInContext(Bento bento) {
        return new Named(bento.getString("name"));
    }
}
