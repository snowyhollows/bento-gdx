package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import net.snowyhollows.bento.gdx.component.CameraFocus;
import net.snowyhollows.bento.gdx.component.Hero;
import net.snowyhollows.bento.gdx.component.Position;

public class CameraFocusOnHeroSystem extends IteratingSystem {

    Position heroPosition;

    public CameraFocusOnHeroSystem() {
        super(CameraFocus.family);
    }

    private Entity getHeroOrNull() {
        final ImmutableArray<Entity> entities = getEngine().getEntitiesFor(Hero.family);
        return entities.size() == 1 ? entities.first() : null;
    }

    @Override
    public void update(float deltaTime) {
        Entity heroOrNull = getHeroOrNull();
        if (heroOrNull != null) {
            heroPosition = Position.mapper.get(heroOrNull);
        }
        super.update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if (heroPosition == null) {
            return;
        }
        CameraFocus cameraFocus = CameraFocus.mapper.get(entity);
        if (cameraFocus.rect.contains(heroPosition.x, heroPosition.y)) {
            cameraFocus.focus = CameraFocus.Focus.ON;
        } else {
            cameraFocus.focus = CameraFocus.Focus.OFF;
        }
    }
}
