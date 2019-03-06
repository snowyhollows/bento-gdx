package net.snowyhollows.bento.gdx.visual.wrapper;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HorizontalWavyVisualElement implements VisualElement<SpriteBatch> {
    private final float amplitude;
    private final float multiplier;
    private final VisualElement visualElement;
    private float counter;

    public HorizontalWavyVisualElement(float amplitude, float time, VisualElement visualElement) {
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
        visualElement.draw(context, x + (float) (rotation + Math.sin(multiplier * counter)) * amplitude, y , rotation, alpha, scale);
    }

    @Override
    public boolean isAnimationFinished() {
        return false;
    }
    
}
