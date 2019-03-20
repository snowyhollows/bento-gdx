package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum RotationFactory implements BentoFactory<Rotation> {
    IT;

    @Override
    public Rotation createInContext(Bento bento) {
        return new Rotation(
                bento.getFloat("rotation")
        );
    }
}
