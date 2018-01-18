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

public class DebugDisplaySystem extends IteratingSystem {
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    public SpriteBatch batch;
    public OrthographicCamera camera;

    float size;
    Color color;


    public DebugDisplaySystem(SpriteBatch batch, OrthographicCamera camera, float size, String colorHex) {
        this();
        this.batch = batch;
        this.camera = camera;
        this.size = size;
        this.color = Color.valueOf(colorHex);
    }

    public DebugDisplaySystem() {
        super(Family.all(Position.class).get());
    }

    @Override
    public void update(float deltaTime) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        super.update(deltaTime);
        shapeRenderer.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Position position = Position.mapper.get(entity);
        shapeRenderer.circle(position.x, position.y, size / 2);
    }
}
