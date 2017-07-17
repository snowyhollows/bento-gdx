package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 *
 * @author filip
 */
public class Joystick extends EntitySystem  {
    public static int sliceLeft;
    public static int sliceRight;

    public static boolean left;
    public static boolean right;
    public static  boolean up;

    @Override
    public void update(float deltaTime) {
        update();

    }

    public static void update() {
        left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        up = Gdx.input.isKeyPressed(Input.Keys.UP);

        sliceLeft = Gdx.graphics.getWidth() / 6;
        sliceRight = Gdx.graphics.getWidth() / 2;

        if (Gdx.input.isTouched()) {
            for (int i = 0; i < 10; i++) {
                if (Gdx.input.isTouched(i)) {
                    final float tx = Gdx.input.getX(i);
                    if (tx < sliceLeft) {
                        left = true;
                    } else if (tx < sliceRight) {
                        right = true;
                    } else {
                        up = true;
                    }
                }
            }
        }
    }

}
