package net.snowyhollows.bento.gdx.system;

import net.snowyhollows.bento2.Bento;
import net.snowyhollows.bento2.BentoFactory;

public enum ClearScreenSystemFactory implements BentoFactory<ClearScreenSystem> {
  IT;

  public ClearScreenSystem createInContext(Bento bento) {
    return new ClearScreenSystem();
  }
}
