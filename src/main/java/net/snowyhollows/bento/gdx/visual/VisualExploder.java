package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class VisualExploder implements VisualElement<SpriteBatch> {
    private final VisualElement visualElement;

    public VisualExploder(VisualElement visualElement) {
        this.visualElement = visualElement;
    }

    private float time;
    
    @Override
    public void update(float delta) {
        time += delta;
    }

    @Override
    public void draw(SpriteBatch context, float x, float y) {
        float rotation = time * 720;
        float alpha = 1 - time;
        float scale = (float) (1 + Math.sin(time));
        visualElement.draw(context, x, y, rotation, alpha, scale);
    }

    @Override
    public void draw(SpriteBatch context, float x, float y, float r, float a, float s) {
        visualElement.draw(context, x, y, time * 360, a, s);
    }

    @Override
    public boolean isAnimationFinished() {
        return (time >= 1);
    }
    
}
