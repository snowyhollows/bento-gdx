package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import net.snowyhollows.bento2.annotation.ByName;
import net.snowyhollows.bento2.annotation.WithFactory;

public class Button implements Component {
    public final static ComponentMapper<Button> mapper = ComponentMapper.getFor(Button.class);

    public String event;

    @WithFactory
    public Button(@ByName("button.event") String event) {
        this.event = event;
    }
}
