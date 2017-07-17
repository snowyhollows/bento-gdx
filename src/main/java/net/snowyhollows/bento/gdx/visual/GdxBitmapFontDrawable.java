package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxBitmapFontDrawable implements VisualElement<SpriteBatch> {

    private final BitmapFont font;
    private final String text;
    private final float size;

    public GdxBitmapFontDrawable(BitmapFont font, String text, float size) {
        this.font = font;
        this.text = text;
        this.size = size;
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void draw(SpriteBatch context, float x, float y) {
        draw(context, x, y, 0, 1, 1);
    }

    @Override
    public boolean isAnimationFinished() {
        return true;
    }

    @Override
    public void draw(SpriteBatch context, float x, float y, float rotation, float alpha, float scale) {
        SpriteBatch spriteBatch = context;
        font.setColor(1,1,1,1);
        font.getData().setScale(scale * size);
        font.draw(context, text, x, y);
    }

}
