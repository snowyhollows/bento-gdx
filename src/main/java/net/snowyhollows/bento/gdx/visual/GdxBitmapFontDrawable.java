package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxBitmapFontDrawable implements VisualElement<SpriteBatch> {

    private final BitmapFont font;
    private final String text;
    private final float size;
    private GlyphLayout glyphLayout;
    protected Color color = Color.WHITE;

    public GdxBitmapFontDrawable(BitmapFont font, String text, float size) {
        this.font = font;
        this.text = text;
        this.size = size;
        this.glyphLayout = new GlyphLayout(font, text);
    }

    public float getWidth() {
        return glyphLayout.width;
    }

    public float getHeight() {
        return glyphLayout.height;
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
        Color tmpColor = spriteBatch.getColor();
        spriteBatch.setColor(color.r, color.g, color.b, alpha);
        font.getData().setScale(scale * size);
        font.draw(context, glyphLayout, x - (glyphLayout.width / 2), y + (glyphLayout.height / 2));
        spriteBatch.setColor(tmpColor);
    }

    public GdxBitmapFontDrawable withColor(Color color) {
        this.color = color;
        return this;
    }
}
