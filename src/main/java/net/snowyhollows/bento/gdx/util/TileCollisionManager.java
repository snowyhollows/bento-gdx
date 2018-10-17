package net.snowyhollows.bento.gdx.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class TileCollisionManager<T> implements LevelWalker.Collider<T> {

    private final Array<TiledMapTileLayer> layers = new Array<TiledMapTileLayer>();
    private TiledMapTileLayer collisionLayer;
    private float tileWidth;
    private float tileHeight;
    private int mapWidth;
    private int mapHeight;
    private final Array<T> tiles;

    public T tileForId(int id) {
        return tiles.get(id);
    }

    public int getCols() {
        return mapWidth;
    }

    public int getRows() {
        return mapHeight;
    }

    public TileCollisionManager(TiledMap map, TileFactory<T> tileFactory) {
        int c = 0;

        for (TiledMapTileSet set : map.getTileSets()) {
            c += set.size();
        }

        tiles = new Array<T>(c);
        tiles.size = c;

        for (int i = 1; i < c; i++) {
            tiles.set(i, tileFactory.create(map.getTileSets().getTile(i).getProperties().get("type", String.class)));
        }

        TiledMapTileLayer temp = null;

        for (MapLayer get : map.getLayers()) {
            if (get.getName().equals("hidden")) {
                get.setVisible(false);
            }
            if (get instanceof TiledMapTileLayer) {
                final TiledMapTileLayer layer = (TiledMapTileLayer) get;
                temp = layer;
                layers.add(layer);
                if (layer.getProperties().containsKey("collision")) {
                    if (collisionLayer != null) {
                        throw new RuntimeException("two collision layers in a single map");
                    }
                    collisionLayer = layer;
                }
            }
            
        }
        if (collisionLayer == null && temp != null) {
            Gdx.app.log("chorizo", "No collision layer in the tmx file; falling back to the first tiled layer");
            collisionLayer = temp;
        }
        if (collisionLayer != null) {
            tileWidth = collisionLayer.getTileWidth();
            tileHeight = collisionLayer.getTileHeight();
            mapWidth = collisionLayer.getWidth();
            mapHeight = collisionLayer.getHeight();
        }
    }

    /**
     * map coordinates, rows down, cols right
     *
     * @param row
     * @param col
     * @return
     */
    public T tileAtRowCol(int row, int col, int layer) {
        final TiledMapTileLayer.Cell cell = layers.get(layer).getCell(col, row);
        return cell == null ? null : tileForId(cell.getTile().getId());
    }
    
    public int getTileLayersCount() {
        return layers.size;
    }
    
    /**
     * map coordinates, rows down, cols right
     *
     * @param row
     * @param col
     * @return
     */
    public T tileAtRowCol(int row, int col) {
        final TiledMapTileLayer.Cell cell = collisionLayer.getCell(col, row);
        return cell == null ? null : tileForId(cell.getTile().getId());
    }

    /**
     * world coordinates, y up, x right
     *
     * @param x
     * @param y
     * @return T
     */
    public T tileAtPixel(float x, float y) {
        int col = (int) (x / tileWidth);
        int row = (int) (y / tileHeight);

        return tileAtRowCol(row, col);
    }

    public T tileAtPixel(float x, float y, int z) {
        int col = (int) (x / tileWidth);
        int row = (int) (y / tileHeight);

        return tileAtRowCol(row, col, z);
    }
    
    @Override
    public T collisionWith(float x, float y) {
        return this.tileAtPixel(x, y);
    }

    @Override
    public Rectangle collisionBox(float x, float y, Rectangle rectangle) {
        if (!collides(x,y,0,0)) {
            return null;
        } else {
            rectangle.set(
                    (float) (Math.floor(x / tileWidth) * tileWidth),
                    (float) (Math.floor(y / tileHeight) * tileHeight),
                    tileWidth,
                    tileHeight
            );
            return rectangle;
        }
    }

    private boolean collides(float x, float y, float dx, float dy) {
        return tileAtPixel(x, y) != null;
    }

    public float getTileWidth() {
        return tileWidth;
    }

    public float getTileHeight() {
        return tileHeight;
    }

}
