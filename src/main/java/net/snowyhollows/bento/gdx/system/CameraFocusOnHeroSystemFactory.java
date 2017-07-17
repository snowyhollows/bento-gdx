package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 7/8/2017.
 */
public enum CameraFocusOnHeroSystemFactory implements BentoFactory<CameraFocusOnHeroSystem> {
    IT;
    @Override
    public CameraFocusOnHeroSystem createInContext(Bento bento) {
        return new CameraFocusOnHeroSystem();
    }
}
