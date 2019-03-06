package net.snowyhollows.bento.gdx.visual.wrapper;

public class VerticalWavyVisualElement<T> implements VisualElement<T> {
    private final float amplitude;
    private final float multiplier;
    private final VisualElement<T> visualElement;
    private float counter;

    public VerticalWavyVisualElement(float amplitude, float time, VisualElement<T> visualElement) {
        this.amplitude = amplitude;
        this.multiplier = (float) ((2 * Math.PI) / time);
        this.visualElement = visualElement;
    }

    @Override
    public void update(float delta) {
        counter += delta;
    }

    @Override
    public void draw(T context, float x, float y) {
        draw(context, x, y, 0, 1, 1);
    }

    @Override
    public void draw(T context, float x, float y, float rotation, float alpha, float scale) {
        visualElement.draw(context, x, y + (float) (rotation + Math.sin(multiplier * counter)) * amplitude, rotation, alpha, scale);
    }

    @Override
    public boolean isAnimationFinished() {
        return false;
    }
    
}
