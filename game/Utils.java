package game;

import java.awt.Graphics2D;

public class Utils {
    public static int centerX(Graphics2D g2, String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return (int) GameScreen.gameWidth / 2 - length / 2;
    }

    public static float getAngle(double x1, double y1, double x2, double y2) {
        float angle = (float) Math.toDegrees(Math.atan2((double) (y1 - y2), (double) (x1 - x2)));
        angle = (angle + 180.0f) % 360.0f;
        angle = 360.0f - angle;

        return angle;
    }
}
