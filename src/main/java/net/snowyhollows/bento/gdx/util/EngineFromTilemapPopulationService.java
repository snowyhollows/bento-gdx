package net.snowyhollows.bento.gdx.util;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import net.snowyhollows.bento2.Bento;

import java.util.Iterator;

public class EngineFromTilemapPopulationService {

    public Engine engine;
    public Bento parentBento;
    public TiledMap tiledMap;

    public EngineFromTilemapPopulationService(Engine engine, Bento bento, TiledMap tiledMap) {
        this.engine = engine;
        this.parentBento = bento;
        this.tiledMap = tiledMap;
    }

    public void populate() {
        for (MapLayer layer : tiledMap.getLayers()) {
            Gdx.app.log("chorizo", "layer: " + layer.getName());
            MapProperties layerProps = layer.getProperties();
            Gdx.app.log("chorizo", "layer: " + layerProps.get("components", String.class));
            if (layerProps.containsKey("ignored")) {
                Gdx.app.log("chorizo", "ignoring layer because it is marked as ignored");
                continue;
            }
            for (MapObject ob : layer.getObjects()) {
                if (ob.getProperties().get("type") == null) continue;
                String type = ob.getProperties().get("type").toString();
                Bento objectBento = parentBento.create();
                Gdx.app.log("chorizo", "object: " + ob + "; name: " + ob.getName());
                objectBento.register("name", ob.getName());
                Iterator<String> keys = ob.getProperties().getKeys();
                while (keys.hasNext()) {
                    final String key = keys.next();
                    objectBento.register(key, ob.getProperties().get(key).toString());
                }
                if (ob instanceof RectangleMapObject) {
                    Rectangle rect = ((RectangleMapObject) ob).getRectangle();
                    objectBento.register("x", rect.x + rect.width / 2);
                    objectBento.register("y", (rect.y + rect.height / 2));
                    objectBento.register("width", (rect.width));
                    objectBento.register("height", (rect.height));
                    objectBento.registerObject("rect", rect);
                    Gdx.app.log("chorizo", "rectangle map object detected: " + rect);
                }
                if (ob instanceof TextureMapObject) {
                    TextureMapObject tmo = (TextureMapObject) ob;
                    objectBento.register("x", (tmo.getX() + tmo.getTextureRegion().getRegionWidth() / 2));
                    // a trick: texture objects coordinates point to BOTTOM-left, all other point to TOP-left. We have to compensate for that.
                    objectBento.register("y", (tmo.getY() + tmo.getTextureRegion().getRegionHeight() * 0.5f));
                    objectBento.register("width", tmo.getTextureRegion().getRegionWidth());
                    objectBento.register("height", tmo.getTextureRegion().getRegionHeight());
                    Gdx.app.log("chorizo", "texture map object detected: " + tmo.getX() + ":" + tmo.getY());
                }
                final Entity entity = objectBento.get(type);
                engine.addEntity(entity);
                Gdx.app.log("chorizo", "adding entity to engine; current count: " + engine.getEntities().size());

                final boolean containsComponents = ob.getProperties().containsKey("components");
                if (containsComponents) {
                    if (containsComponents) {
                        Gdx.app.log("chorizo", "components detected");
                        final String[] components = ob.getProperties().get("components").toString().split("\\s*,\\s*");
                        for (String component : components) {
                            Gdx.app.log("chorizo", "creating component " + component);
                            Component x = (Component) objectBento.get(component);
                            Gdx.app.log("chorizo", "created component: " + x);
                            entity.add(x);
                        }
                    }
                }
            }
        }
    }
}
