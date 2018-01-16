package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Rectangle;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;

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

    @WithFactory
    public CameraFocus(@ByName("focus") Focus focus, @ByName("rect") Rectangle rect) {
        this.focus = focus;
        this.rect = rect;
    }

    @Override
    public void onEvent(String string) {
        focus = Focus.ON;
    }
}
