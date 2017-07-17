package net.snowyhollows.bento.gdx;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import net.snowyhollows.bento.gdx.util.TileCollisionManager;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public class GdxFactories {
    private GdxFactories() {
    }

    public static final BentoFactory<SpriteBatch> SPRITE_BATCH = new BentoFactory<SpriteBatch>() {
        @Override
        public SpriteBatch createInContext(Bento bento) {
            return new SpriteBatch();
        }
    };

    public static final BentoFactory<SpriteBatch> HUD_SPRITE_BATCH = new BentoFactory<SpriteBatch>() {
        @Override
        public SpriteBatch createInContext(Bento bento) {
            return new SpriteBatch();
        }
    };

    public static final BentoFactory<Texture> TEXTURE = new BentoFactory<Texture>() {
        @Override
        public Texture createInContext(Bento bento) {
            return new Texture(bento.getString("texture.filename"));
        }
    };

    public static final BentoFactory<TileCollisionManager> TILE_COLLISION_MANAGER = new BentoFactory<TileCollisionManager>() {
        @Override
        public TileCollisionManager createInContext(Bento bento) {
            return new TileCollisionManager(
                    bento.get(GdxFactories.TILED_MAP),
                    bento.get("tiledmap.factory")
            );
        }
    };

    public static final BentoFactory<OrthographicCamera> ORTHOGRAPHIC_CAMERA = new BentoFactory<OrthographicCamera>() {
        @Override
        public OrthographicCamera createInContext(Bento bento) {
            return new OrthographicCamera();
        }
    };

    public static final BentoFactory<BitmapFont> BITMAP_FONT = new BentoFactory<BitmapFont>() {
        @Override
        public BitmapFont createInContext(Bento bento) {
            return new BitmapFont(Gdx.files.internal(bento.getString("bitmap_font.filename")));
        }
    };

    public static final BentoFactory<OrthographicCamera> HUD_ORTHOGRAPHIC_CAMERA = new BentoFactory<OrthographicCamera>() {
        @Override
        public OrthographicCamera createInContext(Bento bento) {
            return new OrthographicCamera();
        }
    };

    public static final BentoFactory<TiledMap> TILED_MAP = new BentoFactory<TiledMap>() {

        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        @Override
        public TiledMap createInContext(Bento bento) {
            return tmxMapLoader.load(bento.getString("tiledmap.filename"));
        }
    };

    public static final BentoFactory<TiledMapRenderer> TILED_MAP_RENDERER = new BentoFactory<TiledMapRenderer>() {
        @Override
        public TiledMapRenderer createInContext(Bento bento) {
            return new OrthogonalTiledMapRenderer(
                    bento.get(GdxFactories.TILED_MAP),
                    bento.get(GdxFactories.SPRITE_BATCH)
            );
        }
    };

    public static final BentoFactory<Engine> ENGINE = new BentoFactory<Engine>() {
        @Override
        public Engine createInContext(Bento bento) {
            return new Engine();
        }
    };

}
