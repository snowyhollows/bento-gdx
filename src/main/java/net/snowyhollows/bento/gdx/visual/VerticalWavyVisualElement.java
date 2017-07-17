package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VerticalWavyVisualElement implements VisualElement<SpriteBatch> {
    private final float amplitude;
    private final float multiplier;
    private final VisualElement visualElement;
    private float counter;

    public VerticalWavyVisualElement(float amplitude, float time, VisualElement visualElement) {
        this.amplitude = amplitude;
        this.multiplier = (float) ((2 * Math.PI) / time);
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
        visualElement.draw(context, x, y + (float) (rotation + Math.sin(multiplier * counter)) * amplitude, rotation, alpha, scale);
    }

    @Override
    public boolean isAnimationFinished() {
        return false;
    }
    
}
