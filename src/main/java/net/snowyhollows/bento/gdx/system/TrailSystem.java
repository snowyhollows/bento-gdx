package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.snowyhollows.bento.gdx.component.BoundingBox;
import net.snowyhollows.bento.gdx.component.Position;
import net.snowyhollows.bento.gdx.component.Trail;

public class TrailSystem extends IteratingSystem {

    public TrailSystem() {
        super(Family.all(Position.class, Trail.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Trail trail = Trail.mapper.get(entity);
        Position position = Position.mapper.get(entity);

        trail.addPosition(deltaTime, position);
    }
}
