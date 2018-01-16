package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento.gdx.factory.TiledMapFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum RegionsFromTilesCreatorFactory implements BentoFactory<RegionsFromTilesCreator> {
    IT;

    @Override
    public RegionsFromTilesCreator createInContext(Bento bento) {
        return new RegionsFromTilesCreator(bento.get(TiledMapFactory.IT));
    }
}
