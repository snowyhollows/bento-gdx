package net.snowyhollows.bento.gdx.factory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum BitmapFontFactory implements BentoFactory<BitmapFont> {
    IT;
    @Override

    public BitmapFont createInContext(Bento bento) {
        return new BitmapFont(Gdx.files.internal(bento.getString("bitmap_font.filename")));
    }
}
