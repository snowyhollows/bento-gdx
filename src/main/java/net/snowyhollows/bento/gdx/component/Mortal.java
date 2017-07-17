package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class Mortal implements Component{
    public final static ComponentMapper<Mortal> mapper = ComponentMapper.getFor(Mortal.class);
    public State state = State.ALIVE;

    public enum State {
        ALIVE,
        ONLY_JUST_KILLED,
        JUST_KILLED,
        KILLED;

        public boolean isAlive() {
            return this == ALIVE;
        }
    }
}
