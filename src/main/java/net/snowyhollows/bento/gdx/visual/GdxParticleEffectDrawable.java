package net.snowyhollows.bento.gdx.visual;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxParticleEffectDrawable implements VisualElement<SpriteBatch> {

    ParticleEffect particleEffect;

    public GdxParticleEffectDrawable(ParticleEffect particleEffect) {
        this.particleEffect = new ParticleEffect(particleEffect);
        this.particleEffect.start();
    }

    @Override
    public void draw(SpriteBatch context, float x, float y) {
        draw(context, x, y, 0, 1, 1);
    }

    @Override
    public void update(float delta) {
        particleEffect.update(delta);
    }

    @Override
    public boolean isAnimationFinished() {
        return particleEffect.isComplete();
    }

    @Override
    public void draw(SpriteBatch context, float x, float y, float rotation, float alpha, float scale) {
        SpriteBatch batch = (SpriteBatch) context;
        particleEffect.setPosition(x, y);
        particleEffect.draw(batch);
    }
}
