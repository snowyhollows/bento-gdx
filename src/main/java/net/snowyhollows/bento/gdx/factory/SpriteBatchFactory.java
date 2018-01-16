package net.snowyhollows.bento.gdx.factory;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum SpriteBatchFactory implements BentoFactory<SpriteBatch> {
    IT;
    @Override
    public SpriteBatch createInContext(Bento bento) {
        return new SpriteBatch();
    }
}