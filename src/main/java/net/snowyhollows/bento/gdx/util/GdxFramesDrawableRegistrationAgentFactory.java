package net.snowyhollows.bento.gdx.util;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum GdxFramesDrawableRegistrationAgentFactory implements BentoFactory<GdxFramesDrawableRegistrationAgent> {
  IT;

  public GdxFramesDrawableRegistrationAgent createInContext(Bento bento) {
    return new GdxFramesDrawableRegistrationAgent(bento.get(TextureRegionAnimationsProviderFactory.IT), bento);
  }
}
