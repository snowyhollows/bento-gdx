package net.snowyhollows.bento.gdx.visual.wrapper;

public class FrontAndBackDrawable<T> implements VisualElement<T> {

    final VisualElement<T> back;
    final VisualElement<T> front;

    public FrontAndBackDrawable(VisualElement<T> back, VisualElement<T> front) {
        super();
        this.back = back;
        this.front = front;
    }

    @Override
    public void update(float delta) {
        back.update(delta);
        front.update(delta);
    }

    @Override
    public void draw(T context, float x, float y) {
        draw(context, x, y, 0, 1, 1);
    }

    @Override
    public boolean isAnimationFinished() {
        return back.isAnimationFinished() && front.isAnimationFinished();
    }

    @Override
    public void draw(T context, float x, float y, float rotation, float alpha, float scale) {
        back.draw(context, x, y, rotation, alpha, scale);
        front.draw(context, x, y, rotation, alpha, scale);
    }

}
