package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class Named implements Component{

    public static ComponentMapper<Named> mapper = ComponentMapper.getFor(Named.class);

    public String name;

    public Named(String name) {
        this.name = name;
    }
}
