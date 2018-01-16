package net.snowyhollows.bento.gdx.factory;

import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum TiledMapRendererFactory implements BentoFactory<TiledMapRenderer> {
    IT;
    @Override
    public TiledMapRenderer createInContext(Bento bento) {
        return new OrthogonalTiledMapRenderer(
                bento.get(TiledMapFactory.IT),
                bento.get(SpriteBatchFactory.IT)
        );
    }
}
