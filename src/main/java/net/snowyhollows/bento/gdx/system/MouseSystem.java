package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import net.snowyhollows.bento.gdx.component.Position;

public class MouseSystem extends EntitySystem {

    private final OrthographicCamera camera;
    private final float sensitivity;
    public float x;
    public float y;
    public boolean tracking;

    private final Vector3 tempVector = new Vector3();
    private float trackedX;
    private float trackedY;

    public MouseSystem(OrthographicCamera camera, float sensitivity) {
        this.camera = camera;
        this.sensitivity = sensitivity;
    }

    @Override
    public void update(float deltaTime) {
        if (!Gdx.input.isTouched()) {
            tracking = false;
        } else {
            tempVector.x = Gdx.input.getX();
            tempVector.y = Gdx.input.getY();
            camera.unproject(tempVector);

            if (!tracking) {
                tracking = true;
                trackedX = tempVector.x;
                trackedY = tempVector.y;
            } else {
                float dx = (tempVector.x - trackedX);
                float dy = (tempVector.y - trackedY);
                x += dx * sensitivity;
                y += dy * sensitivity;
                trackedX += dx;
                trackedY += dy;
            }
        }
    }
}
