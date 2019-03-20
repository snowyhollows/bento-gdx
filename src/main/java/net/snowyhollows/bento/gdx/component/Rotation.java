package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;

public class Rotation implements Component{
    public static Family family = Family.all(Rotation.class).get();
    public static ComponentMapper<Rotation> mapper = ComponentMapper.getFor(Rotation.class);
    public float rotation;

    public Rotation(float degrees) {
    	this.rotation = degrees;
    }
}
