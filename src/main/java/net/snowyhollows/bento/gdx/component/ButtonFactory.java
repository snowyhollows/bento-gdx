package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

/**
 * Created by fdreger on 6/27/2017.
 */
public enum ButtonFactory implements BentoFactory<Button> {
    IT;
    @Override
    public Button createInContext(Bento bento) {
        return new Button(bento.getString("button.event"));
    }
}
