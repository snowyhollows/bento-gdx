package net.snowyhollows.bento.gdx.visual.wrapper;

public interface VisualElement<T> {

    void update(float delta);

    void draw(T context, float x, float y);

    void draw(T context, float x, float y, float rotation, float alpha, float scale);

    boolean isAnimationFinished();
}
