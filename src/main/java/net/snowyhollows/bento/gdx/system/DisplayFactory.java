package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento.gdx.GdxFactories;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum DisplayFactory implements BentoFactory<Display> {
    IT;
    @Override
    public Display createInContext(Bento bento) {
        return new Display(bento.get(GdxFactories.SPRITE_BATCH), bento.get(GdxFactories.ORTHOGRAPHIC_CAMERA));
    }
}
