package net.snowyhollows.bento.gdx.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.snowyhollows.bento.gdx.visual.gdxspritebatch.GdxFramesDrawable;
import net.snowyhollows.bento.gdx.visual.wrapper.VisualElement;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;
import net.snowyhollows.bento2.annotation.WithFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GdxFramesDrawableRegistrationAgent {
    private final TextureRegionAnimationsProvider textureRegionAnimationsProvider;
    private final Bento bento;

    @WithFactory
    public GdxFramesDrawableRegistrationAgent(TextureRegionAnimationsProvider textureRegionAnimationsProvider, Bento bento) {
        this.textureRegionAnimationsProvider = textureRegionAnimationsProvider;
        this.bento = bento;
    }

    public void register() {
        for (Map.Entry<String, TextureRegionAnimation> entry : textureRegionAnimationsProvider.extractAll().entrySet()) {
            TextureRegionAnimation value = entry.getValue();
            register(entry.getKey(), value.regions, value.intervals);
        }
    }

    private void register(String name, final TextureRegion[] regions, final int[] grains) {
        final int width = regions[0].getRegionWidth();
        final int height = regions[0].getRegionHeight();

        final List<TextureRegion> regionsFlipped = new ArrayList<TextureRegion>();
        for (TextureRegion region : regions) {
            TextureRegion flipped = new TextureRegion(region);
            flipped.flip(true, false);
            regionsFlipped.add(flipped);
        }

        final float fps = 1000f / grains[0];

        bento.register("visual." + name, new BentoFactory<VisualElement<?>>() {
            @Override
            public VisualElement<?> createInContext(Bento bento) {
                return new GdxFramesDrawable(
                        regions,
                        fps,
                        width,
                        height
                );
            }
        });
        bento.register("visual." + name + ".static", new BentoFactory<VisualElement<?>>() {
            @Override
            public VisualElement<?> createInContext(Bento bento) {
                return new GdxFramesDrawable(
                        new TextureRegion[]{regions[0]},
                        fps,
                        width,
                        height
                );
            }
        });
        bento.register("visual." + name + ".static.vflipped", new BentoFactory<VisualElement<?>>() {
            @Override
            public VisualElement<?> createInContext(Bento bento) {
                return new GdxFramesDrawable(
                        new TextureRegion[]{regionsFlipped.get(0)},
                        fps,
                        width,
                        height
                );
            }
        });
        bento.register("visual." + name + ".vflipped", new BentoFactory<VisualElement<?>>() {
            @Override
            public VisualElement<?> createInContext(Bento bento) {
                return new GdxFramesDrawable(
                        regionsFlipped.toArray(new TextureRegion[]{}),
                        fps,
                        width,
                        height
                );
            }
        });
    }
}
