package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;

public class Position implements Component{
    public static Family family = Family.all(Position.class).get();
    public static ComponentMapper<Position> mapper = ComponentMapper.getFor(Position.class);
    public float x;
    public float y;
    public float z;

	public Position(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public float distanceFrom(Position other) {
        return distanceFrom(other.x, other.y);
    }

    public float distanceFrom(float ox, float oy) {
        float dx = ox - x;
        float dy = oy - y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

	public float distanceFrom(float ox, float oy, float oz) {
		float dx = ox - x;
		float dy = oy - y;
		float dz = oz - z;
		return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
	}


	public void copyFrom(Position position) {
        x = position.x;
        y = position.y;
        z = position.z;
    }

	@Override
	public String toString() {
		return "Position{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}
}
