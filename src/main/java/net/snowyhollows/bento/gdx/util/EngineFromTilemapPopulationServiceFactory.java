package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento.gdx.factory.EngineFactory;
import net.snowyhollows.bento.gdx.factory.TiledMapFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum EngineFromTilemapPopulationServiceFactory implements BentoFactory<EngineFromTilemapPopulationService> {
    IT;
    @Override
    public EngineFromTilemapPopulationService createInContext(Bento bento) {
        return new EngineFromTilemapPopulationService(bento.get(EngineFactory.IT), bento, bento.get(TiledMapFactory.IT));
    }
}
