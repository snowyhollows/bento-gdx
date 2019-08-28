package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum CustomDisplayFactory implements BentoFactory<CustomDisplay> {
    IT;
    @Override
    public CustomDisplay createInContext(Bento bento) {
        return new CustomDisplay();
    }
}
