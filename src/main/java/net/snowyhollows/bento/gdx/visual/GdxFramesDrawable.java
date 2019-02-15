package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class GdxFramesDrawable implements VisualElement<SpriteBatch> {

    
    protected float counter;
    protected int current;
    protected float timePerFrame;
    protected int width;
    protected int height;
    protected TextureRegion[] regions;
    protected Color color = Color.WHITE;

    public GdxFramesDrawable(Array<? extends TextureRegion> regionsArray, float fps, int width, int height) {
        this((TextureRegion[])regionsArray.toArray(TextureRegion.class), fps, width, height);
    }

    public GdxFramesDrawable(TextureRegion[] regions, float fps, int width, int height) {
        this.regions = regions;
        timePerFrame = 1 / fps;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(float delta) {
        counter += delta;
        boolean changed = false;
        while (counter > timePerFrame) {
            changed = true;
            counter -= timePerFrame;
            current++;
        }
        if (changed) {
            current = current % regions.length;
        }
    }

    @Override
    public void draw(SpriteBatch context, float x, float y) {
        draw(context, x, y, 0, 1, 1);
    }

    @Override
    public boolean isAnimationFinished() {
        return false;
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float x, float y, float rotation, float alpha, float scale) {
        Color tmpColor = spriteBatch.getColor();
        spriteBatch.setColor(color.r, color.g, color.b, alpha);
        spriteBatch.draw(regions[current], x - width / 2, y - height/ 2,
                width / 2, height / 2, width, height, scale, scale, rotation);
        spriteBatch.setColor(tmpColor);
    }

    public GdxFramesDrawable withColor(Color color) {
        this.color = color;
        return this;
    }

}
