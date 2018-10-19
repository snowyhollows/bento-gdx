
package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.snowyhollows.bento.gdx.component.Position;
import net.snowyhollows.bento.gdx.component.PositionTween;
import net.snowyhollows.bento2.annotation.WithFactory;

public class PositionTweenSystem extends IteratingSystem{

    @WithFactory
    public PositionTweenSystem() {
        super(Family.all(PositionTween.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        PositionTween positionTween = PositionTween.mapper.get(entity);
        Position position = Position.mapper.get(entity);

        positionTween.elapsed += deltaTime;
        float a = Math.max(Math.min(positionTween.elapsed / positionTween.time, 1), 0);
        position.x = positionTween.interpolation.apply(
                positionTween.x1,
                positionTween.x2,
                a);
        position.y = positionTween.interpolation.apply(
                positionTween.y1,
                positionTween.y2,
                a);

        if (positionTween.elapsed > positionTween.time) {
            entity.remove(PositionTween.class);
            if (positionTween.removeAfter) {
                getEngine().removeEntity(entity);
            }
        }
    }
}
