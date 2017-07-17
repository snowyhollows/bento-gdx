package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.snowyhollows.bento.gdx.component.CameraFocus;
import net.snowyhollows.bento.gdx.component.Hero;
import net.snowyhollows.bento.gdx.component.Position;

/**
 * Created by fdreger on 7/8/2017.
 */
public class CameraFocusOnHeroSystem extends IteratingSystem {

    Position heroPosition;

    public CameraFocusOnHeroSystem() {
        super(CameraFocus.family);
    }

    @Override
    public void update(float deltaTime) {
        heroPosition = Position.mapper.get(getEngine().getEntitiesFor(Hero.family).first());
        super.update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CameraFocus cameraFocus = CameraFocus.mapper.get(entity);
        if (cameraFocus.rect.contains(heroPosition.x, heroPosition.y)) {
            cameraFocus.focus = CameraFocus.Focus.ON;
        } else {
            cameraFocus.focus = CameraFocus.Focus.OFF;
        }
    }
}
