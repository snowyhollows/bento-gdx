package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class Button implements Component {
    public final static ComponentMapper<Button> mapper = ComponentMapper.getFor(Button.class);

    public String event;

    public Button(String event) {
        this.event = event;
    }
}
