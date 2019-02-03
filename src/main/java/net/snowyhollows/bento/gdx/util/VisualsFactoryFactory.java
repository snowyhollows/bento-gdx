package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum VisualsFactoryFactory implements BentoFactory<VisualsFactory<?>> {
    IT;

    @Override
    public VisualsFactory<?> createInContext(Bento bento) {
        return new BentoVisualsFactory(bento, "visual.");
    }
}
