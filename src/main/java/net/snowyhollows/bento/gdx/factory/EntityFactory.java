package net.snowyhollows.bento.gdx.factory;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum EntityFactory implements BentoFactory<Entity> {
    IT;
    @Override

    public Entity createInContext(Bento bento) {
        final String entityType = bento.get("type", "");
        final Entity entity = !"".equals(entityType) ? (Entity) (bento.get(entityType)) : new Entity();
        bento.get(EngineFactory.IT).addEntity(entity);

        final String[] components = bento.get("components", "").toString().split("\\s*,\\s*");
        for (String componentName : components) {
            if ("".equals(componentName)) continue;
            Component component = (Component) bento.get(componentName);
            entity.add(component);
        }
        return entity;
    }
}
