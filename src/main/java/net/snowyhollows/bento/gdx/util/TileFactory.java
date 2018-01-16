package net.snowyhollows.bento.gdx.util;

public interface TileFactory<T> {
    T create(String type);
}
