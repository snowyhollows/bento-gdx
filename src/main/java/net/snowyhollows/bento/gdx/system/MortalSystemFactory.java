package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento.gdx.component.Mortal;
import net.snowyhollows.bento.gdx.factory.OrthographicCameraFactory;
import net.snowyhollows.bento.gdx.factory.SpriteBatchFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum MortalSystemFactory implements BentoFactory<MortalSystem> {
    IT;
    @Override
    public MortalSystem createInContext(Bento bento) {
        return new MortalSystem();
    }
}
