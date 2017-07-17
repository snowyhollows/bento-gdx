package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public class GdxRandomSpeedFramesDrawable extends GdxFramesDrawable {

    private static final Random r = new Random();

    public GdxRandomSpeedFramesDrawable(TextureRegion[] regions, float fps,
            int width, int height) {
        super(regions, fps, width, height);
    }

    @Override
    public void update(float delta) {
        super.update(delta * r.nextFloat());
    }
}
