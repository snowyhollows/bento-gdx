package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 6/27/2017.
 */
public enum PositionFactory implements BentoFactory<Position> {
    IT;

    @Override
    public Position createInContext(Bento bento) {
        return new Position(
                bento.getFloat("x"),
                bento.getFloat("y")
        );
    }
}
