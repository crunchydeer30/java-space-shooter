package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

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

    public static void setImage(String imagePath) {
        if (curImage != null) {
            prevImage = curImage;
        }
        curImage = new ImageIcon(imagePath).getImage();
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



        // String curY = "y = " + y + " / " + GameScreen.gameHeight;
        // g2.setFont(new Font("Arial", Font.BOLD, 36));
        // g2.setColor(Color.WHITE);
        // String curEnemies = GameScreen.levelManager.currentLevel.getEnemiesKilled() +  " / " + GameScreen.levelManager.currentLevel.getEnemiesCount();
        // g2.drawString(curEnemies, 0, (int) (GameScreen.gameHeight * 0.7));
        // g2.drawString(curY, 0, (int) (GameScreen.gameHeight * 0.8));
        // String curLvl = "Level " + GameScreen.levelManager.currentLevelIdx;
        // g2.drawString(curLvl, 0, (int) (GameScreen.gameHeight * 0.9));
    }
}
