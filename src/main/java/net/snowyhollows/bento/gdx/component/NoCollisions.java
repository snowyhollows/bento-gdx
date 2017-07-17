package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class NoCollisions implements Component{

    public static ComponentMapper<NoCollisions> mapper = ComponentMapper.getFor(NoCollisions.class);

}
