package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Rectangle;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;

public class BoundingBox implements Component {
    public final static ComponentMapper<BoundingBox> mapper = ComponentMapper.getFor(BoundingBox.class);

    public Rectangle rect;

    @WithFactory
    public BoundingBox(@ByName("rect") Rectangle rect) {
        this.rect = rect;
    }
}
