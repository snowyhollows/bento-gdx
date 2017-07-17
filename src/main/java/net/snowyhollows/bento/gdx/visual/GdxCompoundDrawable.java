package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxCompoundDrawable implements VisualElement<SpriteBatch> {

    final VisualElement back;
    final VisualElement front;

    public GdxCompoundDrawable(VisualElement back, VisualElement front) {
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
    public void draw(SpriteBatch context, float x, float y) {
        draw(context, x, y, 0, 1, 1);
    }

    @Override
    public boolean isAnimationFinished() {
        return back.isAnimationFinished() && front.isAnimationFinished();
    }

    @Override
    public void draw(SpriteBatch context, float x, float y, float rotation, float alpha, float scale) {
        back.draw(context, x, y, rotation, alpha, scale);
        front.draw(context, x, y, rotation, alpha, scale);
    }

}
