package ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import game.GameScreen;

public class Menu {
    Graphics2D g2;

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 40));
        g2.drawString("Menu", GameScreen.gameWidth / 2, GameScreen.gameHeight / 2);
    }
}
