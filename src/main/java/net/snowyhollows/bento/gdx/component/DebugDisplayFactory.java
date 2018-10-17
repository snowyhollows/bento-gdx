package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento.gdx.factory.OrthographicCameraFactory;
import net.snowyhollows.bento.gdx.factory.SpriteBatchFactory;
import net.snowyhollows.bento.gdx.system.DebugDisplaySystem;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum DebugDisplayFactory implements BentoFactory<DebugDisplaySystem> {
    IT;

    @Override
    public DebugDisplaySystem createInContext(Bento bento) {
        return new DebugDisplaySystem(
                bento.get(SpriteBatchFactory.IT),
                bento.get(OrthographicCameraFactory.IT),
                bento.get("debug_display.size"),
                bento.get("debug_display.color"));
    }
}
