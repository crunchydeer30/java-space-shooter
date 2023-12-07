package game;

import java.awt.Graphics2D;
import java.awt.Image;

public class BackgroundManager {
    private static Image curImage = null;
    private static Image prevImage = null;
    private int width = GameScreen.gameWidth;
    private int height = GameScreen.gameHeight;
    private int x = 0;
    private int y = 0;
    private int velocity = 4;

    public BackgroundManager() {

    }

    public static void setBackground(Image backgoundImage) {
        curImage = backgoundImage;
    }

    public void update() {
        y += velocity;
        if (prevImage != null) {
            if (y > height + height) {
                y = 0;
                prevImage = null;
            }
        } else {
            if (y > height) {
                y = 0;
                prevImage = null;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (prevImage != null) {
            g2.drawImage(curImage, x, y - height - height, width, height, null);
            g2.drawImage(prevImage, x, y - height, width, height, null);
            g2.drawImage(prevImage, x, y, width, height, null);
        } else {
            g2.drawImage(curImage, x, y - height, width, height, null);
            g2.drawImage(curImage, x, y, width, height, null);
        }
    }
}
