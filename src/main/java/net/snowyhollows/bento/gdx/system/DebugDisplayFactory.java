package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento.gdx.factory.OrthographicCameraFactory;
import net.snowyhollows.bento.gdx.factory.SpriteBatchFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 7/4/2017.
 */
public enum DebugDisplayFactory implements BentoFactory<DebugDisplay> {
    IT;
    @Override
    public DebugDisplay createInContext(Bento bento) {
        return new DebugDisplay(
                bento.get(SpriteBatchFactory.IT),
                bento.get(OrthographicCameraFactory.IT));
    }
}
