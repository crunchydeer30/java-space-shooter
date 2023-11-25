package game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;

import java.util.ArrayList;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Rectangle;

public class Player {

	public double size = 64;
	private double x;
	private double y;
	private Image sprite;
	private double velocity = 4f;
	public static ArrayList<Bullet> bullets;
	private double rateOfFire = 2f;
	private double shotTime = 0;

	public Player() {
		this.sprite = new ImageIcon("graphics/spaceship.png").getImage();
		bullets = new ArrayList<>();
	}

	public void setPosition(double x, double y) {
		this.x = x - size / 2;
		this.y = y - size / 2;
	};

	public void update(KeyboardManager keyboardManager) {
		if (keyboardManager.isKeyUp) {
			y = y - velocity;
		}

		if (keyboardManager.isKeyDown) {
			y = y + velocity;
		}

		if (keyboardManager.isKeyLeft) {
			x = x - velocity;
		}

		if (keyboardManager.isKeyRight) {
			x = x + velocity;
		}

		if (keyboardManager.isKeySpace) {
			shoot();
		}

		if (y > GameScreen.gameHeight - size) {
			y = GameScreen.gameHeight - size;
		} else if (y < 0) {
			y = 0;
		}

		if (x > GameScreen.gameWidth) {
			x = size;
		} else if (x < 0) {
			x = GameScreen.gameWidth - size;
		}

		if (bullets.size() > 0) {
			for (int i = 0; i < bullets.size(); i++) {
				if (!bullets.get(i).inBounds(1366, 768)) {
					bullets.remove(i);
				}
				bullets.get(i).update();
			}
		}

	}

	public void shoot() {
		if (shotTime == 0) {
			bullets.add(new Bullet(x + size / 2 - 5, y, 10, Color.ORANGE, 8f, -Math.cos(Math.toRadians(90)),
					-Math.sin(Math.toRadians(90))));
		}
		shotTime += rateOfFire;
		if (shotTime > 50) {
			shotTime = 0;
		}
	}

	public void draw(Graphics2D g2) {
		AffineTransform oldTransform = g2.getTransform();
		g2.translate(x, y);

		AffineTransform tx = new AffineTransform();
		tx.scale(size / sprite.getWidth(null), size / sprite.getHeight(null));
		g2.drawImage(sprite, tx, null);

		g2.setTransform(oldTransform);

		Shape hitboxShape = getHitbox();
		g2.setColor(Color.RED);
		g2.draw(hitboxShape.getBounds2D());

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g2);
		}
	}

	public Rectangle getHitbox() {
		return new Rectangle((int) x, (int) y, (int) size, (int) size);
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
}
