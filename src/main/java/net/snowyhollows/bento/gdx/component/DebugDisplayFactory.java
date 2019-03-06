package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum DebugDisplayFactory implements BentoFactory<DebugDisplay> {
    IT;

    @Override
    public DebugDisplay createInContext(Bento bento) {
        return new DebugDisplay(
                bento.getFloat("debugdisplay.size"),
                bento.getEnum(DebugDisplay.Shape.class, "debugdisplay.shape"),
                bento.getString("debugdisplay.color"));
    }
}
