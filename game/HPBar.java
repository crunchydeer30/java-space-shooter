package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class HPBar {
    private final HP hp;

    public HPBar(HP hp) {
        this.hp = hp;
    }

    protected void HPRender(Graphics2D g2, Rectangle bounds, double y) {
        double hpY = bounds.y + y;
        g2.setColor(Color.RED);
        g2.fill(new Rectangle2D.Double(0, hpY, GameScreen.currentLevel.getPlayer().size, 2));
        double hpSize = hp.getCurrent_HP() / hp.getMax_HP() * GameScreen.currentLevel.getPlayer().size;
        g2.fill(new Rectangle2D.Double(0, hpY, hpSize, 2));
    }
}
