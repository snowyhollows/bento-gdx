package net.snowyhollows.bento.gdx.util;

/**
 * Created by fdreger on 6/28/2017.
 */
public class StringTileFactory implements TileFactory<String> {
    @Override
    public String create(String type) {
        return type;
    }
}
