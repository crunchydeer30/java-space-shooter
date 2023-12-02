package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import game.GameScreen;

public class MenuItem {
    private String text;
    private Font font;
    private int x;
    private int y;
    private Color color;
    private Graphics2D g2;
    private boolean isSelected = false;

    public MenuItem (String text, Font font, Color color, int x, int y) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void setIsSelected(boolean value) {
        isSelected = value;
    }

    public boolean getIsSelected() {
        return this.isSelected;
    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(font);
        if (isSelected) {
            g2.setColor(Color.BLUE);
        } else {
            g2.setColor(color);
        }
        g2.drawString(text, centerX(text), y);
    }

    public void update() {
        
    }

    public int centerX(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return GameScreen.gameWidth / 2 - length / 2;
    }
}
