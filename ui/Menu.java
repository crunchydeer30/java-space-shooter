package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.GameScreen;
import game.GameState;

public class Menu {
    Graphics2D g2;
    Image backgroundImage = new ImageIcon("graphics/menu.jpg").getImage();

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.drawImage(backgroundImage, 0, 0, GameScreen.gameWidth, GameScreen.gameHeight, null);
        g2.setFont(new Font("Arial", Font.BOLD, 52));
        g2.setColor(Color.BLACK);
        g2.drawString("PRESS ENTER TO START", centerText("PRESS ENTER TO START"), (int) (GameScreen.gameHeight * 0.65));
        g2.setColor(Color.WHITE);
        g2.drawString("PRESS ENTER TO START", centerText("PRESS ENTER TO START") + 2, (int) (GameScreen.gameHeight * 0.65) + 2);
    }

    public int centerText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return GameScreen.gameWidth / 2 - length / 2;
    }

    public void update() {
        if (GameScreen.keyboardManager.isKeyEnter) {
            GameScreen.stateManager.setGameState(GameState.GAME);
        }
    }
}
