package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import net.snowyhollows.bento.gdx.component.DebugDisplay;
import net.snowyhollows.bento.gdx.component.Position;

public class DebugDisplaySystem extends IteratingSystem {
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private final OrthographicCamera camera;

    public DebugDisplaySystem(OrthographicCamera camera) {
        super(Family.all(Position.class, DebugDisplay.class).get());
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        super.update(deltaTime);
        shapeRenderer.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        DebugDisplay debugDisplay = DebugDisplay.mapper.get(entity);
        Position position = Position.mapper.get(entity);
        shapeRenderer.setColor(debugDisplay.color);
        float halfSize = debugDisplay.size / 2;
        float size = halfSize * 2;
        switch (debugDisplay.shape) {
            case CIRCLE:
                shapeRenderer.circle(position.x, position.y, halfSize);
                break;
            case SQUARE:
                shapeRenderer.rect(position.x - halfSize, position.y - halfSize, size, size);
                break;
        }
    }
}
