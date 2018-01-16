package net.snowyhollows.bento.gdx.util;

public class StringTileFactory implements TileFactory<String> {
    @Override
    public String create(String type) {
        return type;
    }
}
