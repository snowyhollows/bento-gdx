package net.snowyhollows.bento.gdx.component;

import com.badlogic.gdx.math.Rectangle;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 6/27/2017.
 */
public enum CameraFocusFactory implements BentoFactory<CameraFocus> {
    IT;


    @Override
    public CameraFocus createInContext(Bento bento) {
        return new CameraFocus(bento.getEnum(CameraFocus.Focus.class, "focus"), (Rectangle) bento.get("rect"));
    }
}
