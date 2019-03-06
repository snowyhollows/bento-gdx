package net.snowyhollows.bento.gdx.factory;

import com.badlogic.gdx.utils.viewport.FitViewport;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum FitViewportFactory implements BentoFactory<FitViewport> {
    IT;
    @Override
    public FitViewport createInContext(Bento bento) {
        return new FitViewport(bento.getFloat("fitviewport.width"), bento.getFloat("fitviewport.height"), bento.get(OrthographicCameraFactory.IT));
    }
}
