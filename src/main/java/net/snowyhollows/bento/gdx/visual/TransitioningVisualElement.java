/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author filip
 */
public class TransitioningVisualElement implements VisualElement<SpriteBatch> {

    protected final VisualElement visualElement;
    protected float time;
    protected final float duration;

    public TransitioningVisualElement(VisualElement visualElement) {
        this(visualElement, 1);
    }

    public TransitioningVisualElement(VisualElement visualElement, float duration) {
        this.visualElement = visualElement;
        this.duration = duration;
    }

    @Override
    public void update(float delta) {
        time += delta;
    }

    @Override
    public void draw(SpriteBatch context, float x, float y) {
        draw(context, x, y, 0, 1, 1);
    }

    protected float calcY(float y, float normalized) {
        return y;
    }

    protected float calcX(float x, float normalized) {
        return x;
    }

    protected float calcScale(float normalized) {
        return 1;
    }

    protected float calcAlpha(float normalized) {
        return 1;
    }

    protected float calcRotation(float normalized) {
        return 0;
    }

    @Override
    public void draw(SpriteBatch context, float x, float y, float r, float a, float s) {
        float normalized = time / duration;
        float rotation = calcRotation(normalized) + r;
        float alpha = calcAlpha(normalized) * a;
        float scale = calcScale(normalized) * s;
        float animatedX = calcX(x, normalized);
        float animatedY = calcY(y, normalized);
        visualElement.draw(context, animatedX, animatedY, rotation, alpha, scale);
    }

    @Override
    public boolean isAnimationFinished() {
        return time >= duration;
    }
    
}
