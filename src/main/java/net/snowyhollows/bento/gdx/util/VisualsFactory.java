package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento.gdx.visual.wrapper.VisualElement;

public interface VisualsFactory<T> {
    VisualElement<T> get(String name, VisualElement<T> current);
}
