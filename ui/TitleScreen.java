package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.GameScreen;
import stateManager.GameStateType;

public class TitleScreen {
    private int duration = 0;
    private Image image = new ImageIcon("assets/graphics/title.png").getImage();


    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, GameScreen.gameWidth, GameScreen.gameHeight);
        g2.drawImage(image, 0, 0, GameScreen.gameWidth, GameScreen.gameHeight, null);
    }

    public void update() {
        duration--;
        if (duration <= 0) {
            GameScreen.stateManager.setGameState(GameStateType.MENU);
        }
    }
}
