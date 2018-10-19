package net.snowyhollows.bento.gdx.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import net.snowyhollows.bento2.annotation.WithFactory;

public class ClearScreenSystem extends EntitySystem {
    public Color backgroundColor = new Color(Color.BLACK);
    @WithFactory
    public ClearScreenSystem() {
    }

    @Override
    public void update(float deltaTime) {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
