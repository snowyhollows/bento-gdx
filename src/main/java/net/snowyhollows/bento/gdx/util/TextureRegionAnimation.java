package net.snowyhollows.bento.gdx.util;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;

public class TextureRegionAnimation {

	public final static int[] ONE_INTERVAL = {1000};

    TextureRegion[] regions;
    int[] intervals;
    int loopDuration;
    private MapProperties mapProperties;

    public TextureRegionAnimation(TextureRegion[] regions, int[] intervals, MapProperties mapProperties) {
        this.regions = regions;
        this.intervals = intervals;
	    this.mapProperties = mapProperties;

	    for(int i = 0; i < intervals.length; ++i) {
		    loopDuration += intervals[i];
	    }
    }

    public TextureRegion[] getRegions() {
        return regions;
    }

    public int[] getIntervals() {
        return intervals;
    }

    public String getProperty(String name) {
    	Object result = mapProperties.get(name);
    	return result == null ? null : result.toString();
    }

    public boolean hasProperty(String name) {
    	return mapProperties.containsKey(name);
    }


	public int getFrameIndexForTime(float time) {
		int currentTime = (int) ((time * 1000) % (long)this.loopDuration);

		for(int i = 0; i < this.intervals.length; ++i) {
			int animationInterval = this.intervals[i];
			if (currentTime <= animationInterval) {
				return i;
			}

			currentTime -= animationInterval;
		}

		throw new IllegalStateException("Someone must have modified intervals.");
	}

	public TextureRegion getTextureRegionForTime(float time) {
    	return regions[getFrameIndexForTime(time)];
	}

	public TextureRegionAnimation createVFlipped() {
    	TextureRegion[] flippedRegions = new TextureRegion[regions.length];
		for (int i = 0; i < regions.length; i++) {
			flippedRegions[i] = new TextureRegion(regions[i]);
			flippedRegions[i].flip(true, false);
		}
		return new TextureRegionAnimation(flippedRegions, intervals, mapProperties);
	}

	public TextureRegionAnimation createStatic() {
    	return new TextureRegionAnimation(new TextureRegion[]{regions[0]}, ONE_INTERVAL, mapProperties);
	}
}
