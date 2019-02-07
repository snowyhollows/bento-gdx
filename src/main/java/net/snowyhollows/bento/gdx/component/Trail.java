package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.GeometryUtils;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import net.snowyhollows.bento2.annotation.WithFactory;

public class Trail implements Component {
    public final static ComponentMapper<Trail> mapper = ComponentMapper.getFor(Trail.class);

    private final Array<Segment> segments;
    private int idx = -1;

    private static class Segment {
        final Position position = new Position(0, 0);
        float dt;
        float dd;
    }

    @WithFactory
    public Trail() {
        int history = 100;
        this.segments= new Array<>(history);
        for (int i = 0; i < history; i++) {
            segments.add(new Segment());
        }
    }

    public Position getNPositionsAgo(int n) {
        return segments.get(indexNStepsBack(n)).position;
    }

    private int indexNStepsBack(int n) {
        return (segments.size + idx - n) % segments.size;
    }

    public Position getNSecondsAgo(float seconds) {
        float current = 0;
        int n = 0;

        while (n++ < segments.size) {
            Segment segment = segments.get(indexNStepsBack(n));
            if (current >= seconds) {
                return segment.position;
            }
            current += segment.dt;
        }
        return segments.get(indexNStepsBack(n - 1)).position;
    }

    public Position getXUnitsAgo(float distance) {
        float current = 0;
        int n = 0;

        while (n++ < segments.size) {
            Segment segment = segments.get(indexNStepsBack(n));
            if (current >= distance) {
                return segment.position;
            }
            current += segment.dd;
        }
        return segments.get(indexNStepsBack(n - 1)).position;
    }

    public boolean isInitialized() {
        return idx != -1;
    }

    public void addPosition(float dt, Position position) {
        if (idx == -1) {
            for (Segment segment : segments) {
                segment.position.copyFrom(position);
                segment.dd = 0;
                segment.dt = 0;
            }
            idx = 0;
        }
        Position last = segments.get(idx).position;
        if (position.x == last.x && position.y == last.y) {
            return;
        }
        idx = indexNStepsBack(-1);
        Segment segment = segments.get(idx);
        segment.position.copyFrom(position);
        segment.dt = dt;
        segment.dd = last.distanceFrom(position);
    }

}
