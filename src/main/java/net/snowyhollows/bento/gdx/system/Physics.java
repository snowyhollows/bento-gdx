package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.snowyhollows.bento.gdx.component.Collision;
import net.snowyhollows.bento.gdx.component.NoGravity;
import net.snowyhollows.bento.gdx.component.Position;
import net.snowyhollows.bento.gdx.component.Speed;
import net.snowyhollows.bento.gdx.util.LevelWalker;
import net.snowyhollows.bento.gdx.util.TileCollisionManager;

public class Physics extends IteratingSystem {
    public LevelWalker.Collider manager;
    public LevelWalker levelWalker;
    public float vmin; // = -5f;
    public float gravity; // = 0.3f;

    public Physics() {
        super(Family.all(Position.class, Speed.class, Collision.class).get());
    }

    public Physics(LevelWalker.Collider manager, LevelWalker levelWalker, float vmin, float gravity) {
        this();
        this.manager = manager;
        this.levelWalker = levelWalker;
        this.vmin = vmin;
        this.gravity = gravity;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Speed speed = Speed.mapper.get(entity);
        Position position = Position.mapper.get(entity);
        Collision collision = Collision.mapper.get(entity);

        if (!NoGravity.mapper.has(entity)) {
            speed.dy -= gravity;
        }
        
        // tarcie przy spadaniu
        if (speed.dy < vmin) {
            speed.dy = vmin;
        }

        levelWalker.jumpTo(position.x, position.y);
        levelWalker.move(manager, collision.getCollisionPoints(), speed.dx, speed.dy);

        speed.collisionUp = levelWalker.isTopCollision();
        speed.collisionDown = levelWalker.isBottomCollision();
        speed.collisionLeft = levelWalker.isLeftCollision();
        speed.collisionRight = levelWalker.isRightCollision();

        speed.tileCollisionLeft = levelWalker.getLeftCollision();
        speed.tileCollisionRight = levelWalker.getRightCollision();
        speed.tileCollisionDown = levelWalker.getBottomCollision();
        speed.tileCollisionUp = levelWalker.getTopCollision();

        if (speed.collisionLeft || speed.collisionRight) {
            speed.dx = 0;
        }

        if (speed.collisionDown || speed.collisionUp) {
            speed.dy = 0;
        }

        position.x = levelWalker.getX();
        position.y = levelWalker.getY();
        
    }
}
