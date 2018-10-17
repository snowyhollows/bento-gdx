package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento.gdx.factory.OrthographicCameraFactory;
import net.snowyhollows.bento.gdx.factory.SpriteBatchFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 7/4/2017.
 */
public enum DebugDisplaySystemFactory implements BentoFactory<DebugDisplaySystem> {
    IT;
    @Override
    public DebugDisplaySystem createInContext(Bento bento) {
        return new DebugDisplaySystem(
                bento.get(SpriteBatchFactory.IT),
                bento.get(OrthographicCameraFactory.IT),
                bento.getFloat("debug_display.size"),
                bento.getString("debug_display.color"));
    }
}
