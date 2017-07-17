package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Rectangle;

/**
 * @author filip
 */
public class CameraFocus implements Component, EventTarget.Observer {
    public final static Family family = Family.all(CameraFocus.class).get();
    public final static ComponentMapper<CameraFocus> mapper = ComponentMapper.getFor(CameraFocus.class);

    public enum Focus {
        ON, OFF
    }

    public Focus focus = Focus.ON;
    public Rectangle rect;

    public CameraFocus(Focus focus, Rectangle rect) {
        this.focus = focus;
        this.rect = rect;
    }

    @Override
    public void onEvent(String string) {
        focus = Focus.ON;
    }
}
