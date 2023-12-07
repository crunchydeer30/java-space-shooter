package stateManager;

import java.awt.Graphics2D;

public abstract class GameState {
  public abstract void update();
  public abstract void draw(Graphics2D g2);
  public abstract void init();
  public abstract void dispose();
}
