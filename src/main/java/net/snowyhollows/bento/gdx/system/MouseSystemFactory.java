package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento.gdx.factory.OrthographicCameraFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum MouseSystemFactory implements BentoFactory<MouseSystem> {
    IT;
    @Override
    public MouseSystem createInContext(Bento bento) {
        return new MouseSystem( bento.get(OrthographicCameraFactory.IT), bento.getFloat("mouse.sensitivity") );
    }
}
