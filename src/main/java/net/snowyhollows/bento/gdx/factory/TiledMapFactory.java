package net.snowyhollows.bento.gdx.factory;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum TiledMapFactory implements BentoFactory<TiledMap> {
    IT;
    TmxMapLoader tmxMapLoader = new TmxMapLoader();
    @Override
    public TiledMap createInContext(Bento bento) {
        return tmxMapLoader.load(bento.getString("tiledmap.filename"));
    }
}
