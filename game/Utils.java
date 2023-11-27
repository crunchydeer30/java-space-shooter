package game;

import java.awt.Graphics2D;

public class Utils {
    public static int centerX(Graphics2D g2, String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (int) GameScreen.gameWidth / 2 - length / 2;
    }
}
