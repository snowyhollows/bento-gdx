package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.snowyhollows.bento.gdx.component.Looks;
import net.snowyhollows.bento.gdx.component.Position;

public class Display extends IteratingSystem {

    public SpriteBatch batch;
    public OrthographicCamera camera;

    public Display(SpriteBatch batch, OrthographicCamera camera) {
        this();
        this.batch = batch;
        this.camera = camera;
    }

    public Display() {
        super(Family.all(Position.class, Looks.class).get());
    }

    @Override
    public void update(float deltaTime) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Position pos = Position.mapper.get(entity);
        Looks looks = Looks.mapper.get(entity);

        if (looks.visualElement != null) {
            looks.visualElement.draw(batch, pos.x, pos.y);
            looks.visualElement.update(deltaTime);
        }
    }

}
