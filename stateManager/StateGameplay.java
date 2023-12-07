package stateManager;

import java.awt.Graphics2D;

import game.GameScreen;

public class StateGameplay extends GameState {
  public void update() {
    GameScreen.backgroundManager.update();
    GameScreen.levelManager.update();
    if (GameScreen.keyboardManager.isKeyEscape) {
      GameScreen.stateManager.setGameState(GameStateType.MENU);
    }
  };

  public void draw(Graphics2D g2) {
    GameScreen.backgroundManager.draw(g2);
    GameScreen.levelManager.currentLevel.draw(g2);
  };

  public void init() {
    GameScreen.levelManager.init();
  };

  public void dispose() {
    GameScreen.levelManager.reset();
  };
}
