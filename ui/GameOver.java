package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.GameScreen;
import game.Utils;
import stateManager.GameStateType;

public class GameOver {
  public Image background = new ImageIcon("assets/graphics/background_6.png").getImage();
  
  public void update() {
    if (GameScreen.keyboardManager.isKeyEnter) {
      GameScreen.stateManager.setGameState(GameStateType.MENU);
      GameScreen.keyboardManager.isKeyEnter = false;
    }
  }

  public void draw(Graphics2D g2) {
    g2.drawImage(background, 0, 0, GameScreen.gameWidth, GameScreen.gameHeight, null);
    g2.setColor(Color.WHITE);
    g2.setFont(new Font("Arial", Font.BOLD, 64));
    g2.drawString("GAME OVER", Utils.centerX(g2, "GAME OVER"), (int) (GameScreen.gameHeight * 0.4));
  }
}
