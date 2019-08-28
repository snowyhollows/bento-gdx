package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import net.snowyhollows.bento2.annotation.WithFactory;

public class CustomDisplay implements Component{
    public static final Family all = Family.all(CustomDisplay.class).get();
    public final static ComponentMapper<CustomDisplay> mapper = ComponentMapper.getFor(CustomDisplay.class);

    @WithFactory
    public CustomDisplay() {
    }
}
