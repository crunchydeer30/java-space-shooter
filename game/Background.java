package game;
import javax.swing.ImageIcon;

import java.awt.Graphics2D;
import java.awt.Image;

public class Background {
    private Image backroundImage;
    private int width;
    private int height;
    private int x = 0;
    private int y = 0;
    private int velocity = 1;

    public Background(int width, int height) {
        this.width = width;
        this.height = height;
        this.backroundImage = new ImageIcon("graphics/background.png").getImage();
    }

    public void update() {
        y += velocity;
        if (y > height) {
            y = 0;
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(backroundImage, x, y-height, width, height, null);
        g2.drawImage(backroundImage, x, y, width, height, null);
    }
}
