package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RotatingVisualElement implements VisualElement<SpriteBatch> {
    private final float speed;
    private final VisualElement visualElement;
    private float counter;

    public RotatingVisualElement(float speed, VisualElement visualElement) {
        this.speed = speed;
        this.visualElement = visualElement;
    }

    @Override
    public void update(float delta) {
        counter += delta;
    }

    @Override
    public void draw(SpriteBatch context, float x, float y) {
        draw(context, x, y, 0, 1, 1);
    }

    @Override
    public void draw(SpriteBatch context, float x, float y, float rotation, float alpha, float scale) {
        visualElement.draw(context, x, y, rotation + counter * this.speed, alpha, scale);
    }

    @Override
    public boolean isAnimationFinished() {
        return false;
    }
    
    
}
