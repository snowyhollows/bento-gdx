package net.snowyhollows.bento.gdx.component;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum EventTargetFactory implements BentoFactory<EventTarget> {
    IT;

    @Override
    public EventTarget createInContext(Bento bento) {
        return new EventTarget(bento.getString("event_factory.event"));
    }
}
