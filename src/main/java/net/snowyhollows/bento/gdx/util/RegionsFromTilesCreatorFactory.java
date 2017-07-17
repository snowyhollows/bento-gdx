package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento.gdx.GdxFactories;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 7/8/2017.
 */
public enum RegionsFromTilesCreatorFactory implements BentoFactory<RegionsFromTilesCreator> {
    IT;

    @Override
    public RegionsFromTilesCreator createInContext(Bento bento) {
        return new RegionsFromTilesCreator(bento.get(GdxFactories.TILED_MAP));
    }
}
