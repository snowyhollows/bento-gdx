package net.snowyhollows.bento.gdx.visual.wrapper;

import java.util.Random;

public class RandomSpeedFramesDrawable<T> implements VisualElement<T> {

    private static final Random r = new Random();
    private VisualElement<T> visualElement;

    public RandomSpeedFramesDrawable(VisualElement<T> visualElement) {
        this.visualElement = visualElement;
    }

    @Override
    public void update(float delta) {
        visualElement.update(delta * r.nextFloat());
    }

    @Override
    public void draw(T context, float x, float y) {
        visualElement.draw(context, x, y);
    }

    @Override
    public void draw(T context, float x, float y, float rotation, float alpha, float scale) {
        visualElement.draw(context, x, y, rotation, alpha, scale);

    }

    @Override
    public boolean isAnimationFinished() {
        return visualElement.isAnimationFinished();
    }
}
