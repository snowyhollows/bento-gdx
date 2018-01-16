package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import net.snowyhollows.bento2.annotation.WithFactory;

public class Deadly implements Component{
    public static final Family all = Family.all(Deadly.class).get();
    public final static ComponentMapper<Deadly> mapper = ComponentMapper.getFor(Deadly.class);

    @WithFactory
    public Deadly() {
    }
}
