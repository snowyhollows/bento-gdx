package net.snowyhollows.bento.gdx.factory;

import com.badlogic.gdx.graphics.OrthographicCamera;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum HudOrthographicCameraFactory implements BentoFactory<OrthographicCamera> {
    IT;

    @Override
    public OrthographicCamera createInContext(Bento bento) {
        return new OrthographicCamera();
    }
}