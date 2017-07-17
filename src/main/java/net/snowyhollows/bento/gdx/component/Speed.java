package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;

public class Speed implements Component{

    public static final ComponentMapper<Speed> mapper = ComponentMapper.getFor(Speed.class);
    public static final Family family = Family.all(Speed.class).get();
    
    public float dx;
    public float dy;

    public Speed(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public boolean collisionUp;
    public boolean collisionDown;
    public boolean collisionLeft;
    public boolean collisionRight;
    
    public void stop() {
        dx = 0;
        dy = 0;
    }
    
    public void resetCollision() {
        collisionUp = false;
        collisionDown = false;
        collisionLeft = false;
        collisionRight = false;
    }
    
    public boolean isZero() {
        return dx == 0 && dy == 0;
    }
    
    public boolean isCollision() {
        return collisionLeft || collisionRight || collisionUp || collisionDown;
    }
}
