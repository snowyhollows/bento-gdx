package net.snowyhollows.bento.gdx.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import net.snowyhollows.bento.gdx.visual.VisualElement;
import net.snowyhollows.bento2.Bento;

public class Looks implements Component{

    public String name;
    public Bento visualsFactory;

    public static ComponentMapper<Looks> mapper = ComponentMapper.getFor(Looks.class);
    public VisualElement visualElement;

    final public void switchTo(String name) {
        if (name != null && name.equals(this.name)) {
            return;
        }
        this.name = name;
        if (name != null && !"".equals(name)) {
            visualElement = visualsFactory.get(name);
        }
    }

    public Looks(String name, Bento visualsFactory) {
        this.visualsFactory = visualsFactory;
        switchTo(name);
    }

}
