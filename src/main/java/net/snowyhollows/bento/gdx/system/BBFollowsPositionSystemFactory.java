package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum BBFollowsPositionSystemFactory implements BentoFactory<BBFollowsPositionSystem> {
    IT;

    @Override
    public BBFollowsPositionSystem createInContext(Bento bento) {
        return new BBFollowsPositionSystem();
    }
}
