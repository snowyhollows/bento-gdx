package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento.gdx.factory.OrthographicCameraFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum CameraFocusSystemFactory implements BentoFactory<CameraFocusSystem> {
    IT;
    @Override
    public CameraFocusSystem createInContext(Bento bento) {
        return new CameraFocusSystem(bento.get(OrthographicCameraFactory.IT));
    }
}
