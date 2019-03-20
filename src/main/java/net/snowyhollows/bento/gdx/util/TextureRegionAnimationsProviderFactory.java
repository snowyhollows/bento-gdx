package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento.gdx.factory.TiledMapFactory;
import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum TextureRegionAnimationsProviderFactory implements BentoFactory<TextureRegionAnimationsProvider> {
  IT;

  public TextureRegionAnimationsProvider createInContext(Bento bento) {
    return new TextureRegionAnimationsProvider(bento.get(TiledMapFactory.IT));
  }
}
