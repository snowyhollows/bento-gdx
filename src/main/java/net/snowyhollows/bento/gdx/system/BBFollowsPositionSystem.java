package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.snowyhollows.bento.gdx.component.BoundingBox;
import net.snowyhollows.bento.gdx.component.Position;

public class BBFollowsPositionSystem extends IteratingSystem {

    public BBFollowsPositionSystem() {
        super(Family.all(Position.class, BoundingBox.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BoundingBox boundingBox = BoundingBox.mapper.get(entity);
        Position position = Position.mapper.get(entity);

        boundingBox.rect.x = position.x - (boundingBox.rect.width / 2);
        boundingBox.rect.y = position.y - (boundingBox.rect.height / 2);
    }
}
