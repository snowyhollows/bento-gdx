package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import net.snowyhollows.bento.gdx.component.Named;

public class NamedSystem extends EntitySystem {
    private static final Family namedFamily = Family.all(Named.class).get();
    
    public Engine engine;

    public NamedSystem(Engine engine) {
        this.engine = engine;
    }

    public Entity findByName(String name) {
        ImmutableArray<Entity> nameds = engine.getEntitiesFor(namedFamily);
        for (Entity named : nameds) {
            if (name.equals(Named.mapper.get(named).name)) {
                return named;
            }
        }
        return null;
    }
}
