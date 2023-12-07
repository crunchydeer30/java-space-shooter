package stateManager;

import java.awt.Graphics2D;

import sounds.MusicPlayer;
import ui.Options;

public class StateOptions extends GameState {
  public Options options = new Options();
  MusicPlayer menuTheme;

  public void update() {
    options.update();
  };

  public void draw(Graphics2D g2) {
    options.draw(g2);
  };

  public void init() {
  };

  public void dispose() {
  };
}
