package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum JoystickFactory implements BentoFactory<Joystick> {
    IT;
    @Override
    public Joystick createInContext(Bento bento) {
        return new Joystick();
    }
}
