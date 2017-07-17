package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 6/27/2017.
 */
public enum SpeedFactory implements BentoFactory<Speed> {
    IT;

    @Override
    public Speed createInContext(Bento bento) {
        return new Speed(
                bento.getFloat("dx"),
                bento.getFloat("dy")
        );
    }
}
