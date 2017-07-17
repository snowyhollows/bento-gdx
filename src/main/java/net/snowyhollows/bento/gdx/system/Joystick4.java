package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 *
 * @author filip
 */
public class Joystick4 extends EntitySystem  {
    public int sliceLeft;
    public int sliceRight;

    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;
    private int sliceBottom;

    @Override
    public void update(float deltaTime) {
        update();
    }

    public void update() {
        left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        up = Gdx.input.isKeyPressed(Input.Keys.UP);
        down = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        sliceLeft = Gdx.graphics.getWidth() / 6;
        sliceRight = Gdx.graphics.getWidth() / 2;

        sliceBottom = sliceLeft;

        if (Gdx.input.isTouched()) {
            for (int i = 0; i < 10; i++) {
                if (Gdx.input.isTouched(i)) {
                    final float tx = Gdx.input.getX(i);
                    final float ty = Gdx.graphics.getHeight() - Gdx.input.getY(i);
                    if (tx < sliceLeft) {
                        left = true;
                    } else if (tx < sliceRight) {
                        right = true;
                    } else if (ty < sliceBottom){
                        down = true;
                    } else {
                        up = true;
                    }
                }
            }
        }
    }

}
