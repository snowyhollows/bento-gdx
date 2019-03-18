package net.snowyhollows.bento.gdx.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureRegionAnimation {
    TextureRegion[] regions;
    int[] intervals;

    public TextureRegionAnimation(TextureRegion[] regions, int[] intervals) {
        this.regions = regions;
        this.intervals = intervals;
    }

    public TextureRegion[] getRegions() {
        return regions;
    }

    public int[] getIntervals() {
        return intervals;
    }
}
