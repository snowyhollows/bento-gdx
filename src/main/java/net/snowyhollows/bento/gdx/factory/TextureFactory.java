package net.snowyhollows.bento.gdx.factory;

import com.badlogic.gdx.graphics.Texture;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum TextureFactory implements BentoFactory<Texture> {
    IT;
    @Override
    public Texture createInContext(Bento bento) {
        return new Texture(bento.getString("texture.filename"));
    }
}