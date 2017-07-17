package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import net.snowyhollows.bento.gdx.component.CameraFocus;
import net.snowyhollows.bento.gdx.component.Position;

public class CameraFocusSystem extends IteratingSystem {

    private int counter;
    private float sumX;
    private float sumY;

    public OrthographicCamera camera;

    public CameraFocusSystem(OrthographicCamera camera) {
        this();
        this.camera = camera;
    }

    public CameraFocusSystem() {
        super(Family.all(CameraFocus.class).get());
    }

    @Override
    public void update(float deltaTime) {
        counter = 0;
        sumX = 0f;
        sumY = 0f;
        super.update(deltaTime);

        if (counter > 0) {
            camera.position.x = sumX / counter;
            camera.position.y = sumY / counter;
            camera.update();
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Position p = Position.mapper.get(entity);
        CameraFocus cameraFocus = CameraFocus.mapper.get(entity);
        if (cameraFocus.focus == CameraFocus.Focus.ON) {
            sumX += p.x;
            sumY += p.y;
            counter++;
        }
    }

}
