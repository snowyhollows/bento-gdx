package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento.gdx.factory.TiledMapFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum TextureRegionAminationsProviderFactory implements BentoFactory<TextureRegionAminationsProvider> {
  IT;

  public TextureRegionAminationsProvider createInContext(Bento bento) {
    return new TextureRegionAminationsProvider(bento.get(TiledMapFactory.IT));
  }
}
