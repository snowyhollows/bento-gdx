package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum PositionTweenSystemFactory implements BentoFactory<PositionTweenSystem> {
  IT;

  public PositionTweenSystem createInContext(Bento bento) {
    return new PositionTweenSystem();
  }
}
