package net.snowyhollows.bento.gdx.visual.wrapper;

public interface VisualElement<T> {

    void update(float delta);

    public void draw(T context, float x, float y);

    public void draw(T context, float x, float y, float rotation, float alpha, float scale);

    public boolean isAnimationFinished();
}
