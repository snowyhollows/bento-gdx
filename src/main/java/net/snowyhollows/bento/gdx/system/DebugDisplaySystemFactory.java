package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento.gdx.factory.OrthographicCameraFactory;
import net.snowyhollows.bento.gdx.factory.SpriteBatchFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum DebugDisplaySystemFactory implements BentoFactory<DebugDisplaySystem> {
    IT;
    @Override
    public DebugDisplaySystem createInContext(Bento bento) {
        return new DebugDisplaySystem(
                bento.get(OrthographicCameraFactory.IT));
    }
}
