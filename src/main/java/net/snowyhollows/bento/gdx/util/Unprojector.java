package net.snowyhollows.bento.gdx.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Unprojector {
    private final OrthographicCamera orthographicCamera;
    private final Viewport viewport;

    public Unprojector(OrthographicCamera orthographicCamera, Viewport viewport) {
        this.orthographicCamera = orthographicCamera;
        this.viewport = viewport;
    }

    public Vector3 unproject(Vector3 screenCoords) {
        if (orthographicCamera != null) {
            return orthographicCamera.unproject(screenCoords);
        } else {
            return viewport.unproject(screenCoords);
        }
    }
}
