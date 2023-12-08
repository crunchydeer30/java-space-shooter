package stateManager;

import java.awt.Graphics2D;

import ui.GameOver;

public class StateGameOver extends GameState {
  public GameOver gameOver = new GameOver();

  public void update() {
    gameOver.update();
  };

  public void draw(Graphics2D g2) {
    gameOver.draw(g2);
  };

  public void init() {
  };

  public void dispose() {
  };
}
