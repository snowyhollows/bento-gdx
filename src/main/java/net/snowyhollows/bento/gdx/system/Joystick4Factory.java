package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum Joystick4Factory implements BentoFactory<Joystick4> {
    IT;
    @Override
    public Joystick4 createInContext(Bento bento) {
        return new Joystick4();
    }
}
