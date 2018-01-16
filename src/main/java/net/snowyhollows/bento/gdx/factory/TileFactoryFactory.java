package net.snowyhollows.bento.gdx.factory;

import net.snowyhollows.bento.gdx.util.StringTileFactory;
import net.snowyhollows.bento.gdx.util.TileFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum  TileFactoryFactory implements BentoFactory<TileFactory> {
    IT;
    @Override
    public TileFactory createInContext(Bento bento) {
        return new StringTileFactory();
    }
}