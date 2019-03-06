package net.snowyhollows.bento.gdx.visual.wrapper;

public class RotatingVisualElement<T> implements VisualElement<T> {
    private final float speed;
    private final VisualElement<T> visualElement;
    private float counter;

    public RotatingVisualElement(float speed, VisualElement<T> visualElement) {
        this.speed = speed;
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
        visualElement.draw(context, x, y, rotation + counter * this.speed, alpha, scale);
    }

    @Override
    public boolean isAnimationFinished() {
        return false;
    }
    
    
}
