package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Interpolation;

public class PositionTween implements Component {
    public final static ComponentMapper<PositionTween> mapper = ComponentMapper.getFor(PositionTween.class);

    public final float time;
    public final float x1;
    public final float y1;
    public final float x2;
    public final float y2;
    public final boolean removeAfter;
    public final Interpolation interpolation;
    public float elapsed;

    public PositionTween(float time, Position from, Position to, boolean removeAfter, Interpolation interpolation) {
        this.time = time;
        this.x1 = from.x;
        this.y1 = from.y;
        this.x2 = to.x;
        this.y2 = to.y;
        this.removeAfter = removeAfter;
        this.interpolation = interpolation;
    }
}
