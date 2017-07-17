package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import net.snowyhollows.bento.gdx.component.Mortal;

public class MortalSystem extends IteratingSystem {

    public MortalSystem() {
        super(Family.all(Mortal.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Mortal mortal = Mortal.mapper.get(entity);
        if (mortal.state == Mortal.State.ONLY_JUST_KILLED) {
            mortal.state = Mortal.State.JUST_KILLED;
        } else if (mortal.state == Mortal.State.JUST_KILLED) {
            mortal.state = Mortal.State.KILLED;
        }
    }

}
