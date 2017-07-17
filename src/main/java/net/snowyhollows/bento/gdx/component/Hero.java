package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;

public class Hero implements Component{
    public static final Family family = Family.all(Hero.class).get();
    public static final ComponentMapper<Hero> mapper = ComponentMapper.getFor(Hero.class);
}
