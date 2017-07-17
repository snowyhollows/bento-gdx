package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface VisualElement<T> {

    void update(float delta);

    public void draw(T context, float x, float y);

    public void draw(T context, float x, float y, float rotation, float alpha, float scale);

    public boolean isAnimationFinished();
}
