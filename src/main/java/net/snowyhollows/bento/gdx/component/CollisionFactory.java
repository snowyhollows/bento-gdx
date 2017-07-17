package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 6/27/2017.
 */
public enum CollisionFactory implements BentoFactory<Collision> {
    IT;

    @Override
    public Collision createInContext(Bento bento) {
        return new Collision(
                bento.getFloat("collision.width"),
                bento.getFloat("collision.height"),
                bento.getFloat("collision.horizontal_offset"),
                bento.getFloat("collision.vertical_offset"),
                bento.getInt("collision.hsubdivision"),
                bento.getInt("collision.vsubdivision")
                );

    }
}
