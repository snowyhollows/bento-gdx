package net.snowyhollows.bento.gdx.component;

import com.badlogic.gdx.math.Rectangle;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum BoundingBoxFactory implements BentoFactory<BoundingBox> {
    IT;
    @Override
    public BoundingBox createInContext(Bento bento) {
        return new BoundingBox((Rectangle) bento.get("rect"));
    }
}
