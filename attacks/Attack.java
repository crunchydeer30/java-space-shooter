package attacks;

import java.awt.Shape;

import game.Entity;

public abstract class Attack {
  public abstract void update();
  public abstract void draw();
  public abstract void registerHit();
  public abstract Shape getHitbox();
  public abstract Entity getEntity();
}
