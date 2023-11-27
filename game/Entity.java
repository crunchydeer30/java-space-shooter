package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Entity {
    public ArrayList<Bullet> bullets = new ArrayList<>();

    public abstract void update();

    public abstract void checkBounds();

    public abstract void shoot();

    public abstract double getX();

    public abstract void setX(double x);

    public abstract double getY();

    public abstract void setY(double y);

    public abstract double getSize();

    public abstract double getWidth();

    public abstract void setWidth(double width);

    public abstract void setHeight(double height);

    public abstract double getHeight();

    public abstract Image getSprite();

    public abstract double getSpeed();

    public abstract double getRateOfFire();

    public abstract double getCurrentHP();

    public abstract void setCurrentHP(double currentHP);

    public abstract double getMaxHP();

    public abstract void setMaxHP(double maxHP);

    public abstract ArrayList<Bullet> getBullets();

    public abstract void move();

    public abstract void registerIncomingDamage();

    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    public Rectangle getHitbox() {
        return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }

    public void drawHP(Graphics2D g2) {
        if (getCurrentHP() / getMaxHP() < 0.3) {
            g2.setColor(Color.RED);       
        } else if (getCurrentHP() / getMaxHP() < 0.5) {
            g2.setColor(Color.ORANGE);
        } else if (getCurrentHP() / getMaxHP() < 0.7) {
            g2.setColor(Color.YELLOW);
        } else {
            g2.setColor(Color.GREEN);
        }

        if (getCurrentHP() != getMaxHP()) {
            g2.fillRect((int) getX(), (int) getY(), (int) (getWidth() * (getCurrentHP() / getMaxHP())), (int)Math.ceil(getHeight() / 25));
        }
    }

    public void drawHitbox(Graphics2D g2) {
        Rectangle hitboxShape = getHitbox();
        g2.setColor(Color.red);
        g2.draw(hitboxShape.getBounds2D());
    }

    public void draw(Graphics2D g2) {

		g2.drawImage(getSprite(), (int)getX(), (int)getY(),  (int)getWidth(), (int)getHeight(), null);

		drawHP(g2);
        // drawHitbox(g2);
        drawBullets(g2);
	}

    public boolean inBounds(int width, int height) {
        Rectangle size = getHitbox().getBounds();
        if (getX() <= -size.getWidth() || getY() < -size.getHeight() || getX() > width || getY() > height) {
            return false;
        }
        return true;
    }

    public void drawBullets(Graphics2D g2) {
        for (int i = 0; i < getBullets().size(); i++) {
            getBullets().get(i).draw(g2);
        }
    }

    public void updateBullets() {
        for (int i = 0; i < getBullets().size(); i++) {
            getBullets().get(i).update();
        }
    }

    public void calculateDimensions() {
        double sizeRatio = (double)((double)getSprite().getWidth(null) / (double)getSprite().getHeight(null));
        setWidth(getSize());
        setHeight(getSize() / sizeRatio);
    }
}
