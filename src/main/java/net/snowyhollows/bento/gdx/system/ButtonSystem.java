package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import net.snowyhollows.bento.gdx.component.BoundingBox;
import net.snowyhollows.bento.gdx.component.Button;
import net.snowyhollows.bento.gdx.component.EventTarget;
import net.snowyhollows.bento.gdx.component.Position;
import net.snowyhollows.bento.gdx.util.Unprojector;

public class ButtonSystem extends IteratingSystem {

    private final Engine engine;
    private final Unprojector unprojector;
    private Entity currentlyPressed;

    public ButtonSystem(Engine engine, Unprojector unprojector) {
        super(Family.all(Button.class, Position.class).get());
        this.engine = engine;
        this.unprojector = unprojector;
    }

    private final Vector3 temp = new Vector3();
    private final Position tempPosition = new Position(0, 0);

    @Override
    public void update(float deltaTime) {
        currentlyPressed = null;
        super.update(deltaTime);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Position position = Position.mapper.get(entity);
        Button button = Button.mapper.get(entity);
        BoundingBox boundingBox = BoundingBox.mapper.get(entity);

        if (Gdx.input.justTouched()) {
            temp.x = Gdx.input.getX();
            temp.y = Gdx.input.getY();
            unprojector.unproject(temp);
            tempPosition.x = temp.x;
            tempPosition.y = temp.y;

            boolean hasBoundingBox = boundingBox != null;
            boolean withinBoundingBox = hasBoundingBox && boundingBox.rect.contains(tempPosition.x, tempPosition.y);
            if (withinBoundingBox) {
                currentlyPressed = entity;
                ImmutableArray<Entity> entities = engine.getEntitiesFor(EventTarget.all);
                for (Entity target : entities) {
                    EventTarget eventTarget = EventTarget.mapper.get(target);
                    if (eventTarget.event == null || eventTarget.event.equals(button.event)) {
                        ImmutableArray<Component> components = target.getComponents();
                        for (Component component : components) {
                            if (component instanceof EventTarget.Observer) {
                                ((EventTarget.Observer)component).onEvent(button.event);
                            }
                        }
                    }
                }
            }
        }
    }

    public Entity getCurrentlyPressed() {
        return currentlyPressed;
    }
}
