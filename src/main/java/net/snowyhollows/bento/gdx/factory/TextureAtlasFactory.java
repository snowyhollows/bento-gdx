package net.snowyhollows.bento.gdx.factory;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum TextureAtlasFactory implements BentoFactory<TextureAtlas> {
    IT;
    @Override
    public TextureAtlas createInContext(Bento bento) {
        return new TextureAtlas(bento.getString("atlas.filename"));
    }
}