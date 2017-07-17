package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.snowyhollows.bento.gdx.component.Collision;
import net.snowyhollows.bento.gdx.component.Looks;
import net.snowyhollows.bento.gdx.component.Position;

public class DebugDisplay extends IteratingSystem {
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    public SpriteBatch batch;
    public OrthographicCamera camera;

    public DebugDisplay(SpriteBatch batch, OrthographicCamera camera) {
        this();
        this.batch = batch;
        this.camera = camera;
    }

    public DebugDisplay() {
        super(Family.all(Position.class, Collision.class).get());
    }

    @Override
    public void update(float deltaTime) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.CYAN);
        super.update(deltaTime);
        shapeRenderer.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Position position = Position.mapper.get(entity);
        Collision collision = Collision.mapper.get(entity);
        Looks looks = Looks.mapper.get(entity);
        if (looks == null || looks.visualElement == null) {
            shapeRenderer.circle(position.x, position.y, collision.width / 2);
        }
    }
}
