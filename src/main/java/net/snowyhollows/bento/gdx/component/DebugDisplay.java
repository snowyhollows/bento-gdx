package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Color;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;

public class DebugDisplay implements Component {
    public final static ComponentMapper<DebugDisplay> mapper = ComponentMapper.getFor(DebugDisplay.class);

    public enum Shape {
        CIRCLE, SQUARE
    }

    public float size;
    public Shape shape;
    public Color color;


    public DebugDisplay(float size, Shape shape, String color) {
        this.size = size;
        this.shape = shape;
        this.color = Color.valueOf(color);
    }
}
