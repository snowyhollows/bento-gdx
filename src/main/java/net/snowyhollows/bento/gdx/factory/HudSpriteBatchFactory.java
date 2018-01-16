package net.snowyhollows.bento.gdx.factory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum HudSpriteBatchFactory implements BentoFactory<SpriteBatch> {
    IT;
    @Override
    public com.badlogic.gdx.graphics.g2d.SpriteBatch createInContext(Bento bento) {
        return new com.badlogic.gdx.graphics.g2d.SpriteBatch();
    }
}