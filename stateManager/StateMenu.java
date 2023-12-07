package stateManager;

import java.awt.Graphics2D;

import sounds.MusicPlayer;
import ui.Menu;

public class StateMenu extends GameState {
  public Menu menu = new Menu();
  public MusicPlayer menuTheme;

  public void update() {
    menu.update();
  };

  public void draw(Graphics2D g2) {
    menu.draw(g2);
  };

  public void init() {
    menuTheme = new MusicPlayer("menu");
    menuTheme.play();
  };

  public void dispose() {
    menuTheme.stop();
  };
}
