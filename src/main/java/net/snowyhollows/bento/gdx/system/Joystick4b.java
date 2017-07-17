package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import net.snowyhollows.bento.gdx.visual.VisualElement;

/**
 *
 * @author filip
 */
public class
Joystick4b extends EntitySystem  {

    public int sliceLeft;
    public int sliceRight;

    public boolean left;
    public boolean right;
    public boolean up;
    public boolean down;
    public boolean exit;

    private OrthographicCamera camera;

    private SpriteBatch otherBatch;

    private final Vector3 vector = new Vector3();
    private VisualElement arrowLeft;
    private VisualElement arrowRight;
    private VisualElement arrowUp;
    private VisualElement arrowDown;
    private VisualElement restart;
    
    @Override
    public void update(float deltaTime) {
        update();
    }

    public void update() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        otherBatch.setProjectionMatrix(camera.combined);
        
        float halfHeight = Gdx.graphics.getHeight() / 2;
        float halfWidth = Gdx.graphics.getWidth() / 2;
        
        float tile = Gdx.graphics.getHeight() / 15;
        
        float row0 = Gdx.graphics.getHeight() - tile;
        float rowA = halfHeight - 2 * tile;
        float rowB = halfHeight ;
        float rowC = halfHeight + 2 * tile;

        float colA = tile;
        float colB = Gdx.graphics.getWidth() - tile;
        
        Joystick.update();
        otherBatch.begin();
        
        arrowLeft.draw(otherBatch, colA, rowB);
        arrowRight.draw(otherBatch, colB, rowB);
        arrowUp.draw(otherBatch, colA, rowA);
        arrowUp.draw(otherBatch, colB, rowA);
        arrowDown.draw(otherBatch, colA, rowC);
        arrowDown.draw(otherBatch, colB, rowC);
        restart.draw(otherBatch, colB, row0);
        
        otherBatch.end();
        
        left = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        right = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        up = Gdx.input.isKeyPressed(Input.Keys.UP);
        down = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        exit = Gdx.input.isKeyPressed(Input.Keys.R);

        if (Gdx.input.isTouched()) {
            for (int i = 0; i < 10; i++) {
                if (Gdx.input.isTouched(i)) {
                    final float tx = Gdx.input.getX(i);
                    final float ty = Gdx.graphics.getHeight() - Gdx.input.getY(i);
                    vector.x = tx;
                    vector.y = ty;
                    
                    if (vector.y > row0 - tile && vector.x > colB - tile) {
                        exit = true;
                        return;
                    }
                    if (vector.y > halfHeight + tile) {
                        up = true;
                        continue;
                    }
                    if (vector.y < halfHeight - tile) {
                        down = true;
                        continue;
                    }
                    if (vector.x < halfWidth) {
                        left = true;
                        continue;
                    }
                    if (vector.x > halfWidth) {
                        right = true;
                        continue;
                    }
                }
            }
        }
    }

    public void init() {
        otherBatch = new SpriteBatch();
        camera = new OrthographicCamera();
    }

}
