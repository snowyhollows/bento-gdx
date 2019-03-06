package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento.gdx.visual.wrapper.VisualElement;
import net.snowyhollows.bento2.Bento;

public class BentoVisualsFactory implements VisualsFactory {

    private final Bento bento;
    private final String prefix;

    public BentoVisualsFactory(Bento bento, String prefix) {
        this.bento = bento;
        this.prefix = prefix;
    }

    @Override
    public VisualElement get(String name, VisualElement current) {
        return bento.get(prefix + name);
    }
}
