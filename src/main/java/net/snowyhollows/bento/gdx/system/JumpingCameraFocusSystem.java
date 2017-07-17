package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Interpolation;
import net.snowyhollows.bento.gdx.component.CameraFocus;
import net.snowyhollows.bento.gdx.component.Position;

public class JumpingCameraFocusSystem extends IteratingSystem {

    public interface CameraState {
        void savePosition(Position position);
        Position loadPosition();
    }

    public OrthographicCamera camera;
    public CameraState cameraState;

    public JumpingCameraFocusSystem(OrthographicCamera camera, CameraState cameraState) {
        this();
        this.camera = camera;
        this.cameraState = cameraState;
    }

    private Position turnedOn;

    public JumpingCameraFocusSystem() {
        super(Family.all(CameraFocus.class).get());
    }
    private Interpolation interpolation = Interpolation.fade;

    private Position lastCameraPosition;
    private Position targetCameraPosition;
    private float targetTransitionTime = 0.75f;
    private float transitionTime = targetTransitionTime;

    @Override
    public void update(float deltaTime) {

        Position savedPosition = cameraState.loadPosition();

        if (savedPosition == null) {

        }

        if (transitionTime >= targetTransitionTime) {
            super.update(deltaTime);

            if (turnedOn != null) {
                if (lastCameraPosition == null) {
                    lastCameraPosition = turnedOn;
                    camera.position.x = lastCameraPosition.x;
                    camera.position.y = lastCameraPosition.y;
                    camera.update();
                } else {
                    targetCameraPosition = turnedOn;
                    transitionTime = 0;
                }
                turnedOn = null;
            }
            return;
        }
        transitionTime += deltaTime;
        float a = transitionTime * (1 / targetTransitionTime);
        camera.position.x = interpolation.apply(lastCameraPosition.x, targetCameraPosition.x, a);
        camera.position.y = interpolation.apply(lastCameraPosition.y, targetCameraPosition.y, a);
        camera.update();
        if (a >= 1) {
            lastCameraPosition = targetCameraPosition;
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CameraFocus focus = CameraFocus.mapper.get(entity);
        Position position = Position.mapper.get(entity);

        if (focus.focus == CameraFocus.Focus.ON) {
            turnedOn = position;
            cameraState.savePosition(turnedOn);
            focus.focus = CameraFocus.Focus.OFF;
        }

    }

    public void panHardTo(Entity entity) {
        Position position = Position.mapper.get(entity);
        turnedOn = position;
        update(1000 * 10);
    }

}
