package net.snowyhollows.bento.gdx.factory;

import com.badlogic.ashley.core.Engine;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum  EngineFactory implements BentoFactory<Engine> {
    IT;
    @Override
    public Engine createInContext(Bento bento) {
        return new Engine();
    }
}