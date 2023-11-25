package enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Random;

import game.Bullet;
import game.GameScreen;

public abstract class Enemy {
    public ArrayList<Bullet> bullets = new ArrayList<>();

    public abstract void shoot();

    public abstract double getX();

    public abstract void setX(double x);

    public abstract double getY();

    public abstract void setY(double y);

    public abstract double getSize();

    public abstract Image getSprite();

    public abstract double getSpeed();

    public abstract double getRateOfFire();

    public abstract double getCurrentHP();

    public abstract void setCurrentHP(double currentHP);

    public abstract double getMaxHP();

    public abstract void setMaxHP(double maxHP);

    public abstract ArrayList<Bullet> getBullets();

    public abstract void move();

    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    public void registerHits() {
        ArrayList<Bullet> playerBullets = GameScreen.currentLevel.getPlayer().getBullets();

        for (int i = 0; i < playerBullets.size(); i++) {
            Bullet bullet = playerBullets.get(i);
            Area bulletHitbox = new Area(bullet.getHitbox());

            if (bulletHitbox.intersects(this.getHitbox())) {
                playerBullets.remove(bullet);
                this.setCurrentHP(this.getCurrentHP() - bullet.getDamage());
                break;
            }
        }
    }

    public void registerPlayerHits() {
        Rectangle playerHitbox = GameScreen.currentLevel.getPlayer().getHitbox();
        ArrayList<Bullet> bullets = getBullets();

        for (int i = 0; i < bullets.size(); i++) {
            Area bulletHitbox = new Area(bullets.get(i).getHitbox());
            if (bulletHitbox.intersects(playerHitbox)) {
                bullets.remove(bullets.get(i));
                break;
            }
        }
    }

    public Rectangle getHitbox() {
        return new Rectangle((int) getX(), (int) getY(), (int) getSize(), (int) getSize());
    }

    public boolean inBounds(int width, int height) {
        Rectangle size = getHitbox().getBounds();
        if (getX() <= -size.getWidth() || getY() < -size.getHeight() || getX() > width || getY() > height) {
            return false;
        }
        return true;
    }

    public void checkBounds() {
        if (!inBounds(GameScreen.gameWidth, GameScreen.gameHeight)) {
            Random rand = new Random();
            setPosition(rand.nextInt(GameScreen.gameWidth), 0);
        }
    }

    public void update() {
        move();
        registerHits();
        registerPlayerHits();
        checkBounds();
        shoot();

        if (getCurrentHP() <= 0) {
            GameScreen.currentLevel.getEnemies().remove(this);
            GameScreen.currentLevel.setEnemiesKilled(GameScreen.currentLevel.getEnemiesKilled() + 1);;
        }

        for (int i = 0; i < getBullets().size(); i++) {
            getBullets().get(i).update();
        }
    }

    public void draw(Graphics2D g2) {
        AffineTransform oldTransform = g2.getTransform();
        g2.translate(getX(), getY());

        AffineTransform tran = new AffineTransform();
        tran.scale(getSize() / getSprite().getWidth(null), getSize() / getSprite().getHeight(null));
        g2.drawImage(getSprite(), tran, null);
        g2.setTransform(oldTransform);

        
        // drawHitbox(g2);
        drawHP(g2);

        for (int i = 0; i < getBullets().size(); i++) {
            getBullets().get(i).draw(g2);
        }
    }

    public void drawHP(Graphics2D g2) {
        g2.setColor(Color.GREEN);
        g2.fillRect((int) getX(), (int) getY() - 5, (int) (getSize() * (getCurrentHP() / getMaxHP())), 2);
    }

    public void drawHitbox(Graphics2D g2) {
        Rectangle hitboxShape = getHitbox();
        g2.setColor(Color.red);
        g2.draw(hitboxShape);
        g2.draw(hitboxShape.getBounds2D());
    }
}
