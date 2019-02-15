package net.snowyhollows.bento.gdx.binder;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum ComponentsBinderFactory implements BentoFactory<ComponentsBinder> {
    IT;
    @Override
    public ComponentsBinder createInContext(Bento bento) {
        return new ComponentsBinder(bento);
    }
}
