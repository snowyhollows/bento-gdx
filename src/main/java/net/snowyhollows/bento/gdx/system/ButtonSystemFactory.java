package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento.gdx.factory.EngineFactory;
import net.snowyhollows.bento.gdx.factory.OrthographicCameraFactory;
import net.snowyhollows.bento.gdx.util.Unprojector;
import net.snowyhollows.bento.gdx.util.UnprojectorFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 7/2/2017.
 */
public enum ButtonSystemFactory implements BentoFactory<ButtonSystem> {
    IT;

    @Override
    public ButtonSystem createInContext(Bento bento) {
        return new ButtonSystem(bento.get(EngineFactory.IT), bento.get(UnprojectorFactory.IT));
    }
}
