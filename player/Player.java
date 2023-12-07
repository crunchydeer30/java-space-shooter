package player;

import game.Entity;
import game.GameScreen;
import game.KeyboardManager;


public abstract class Player extends Entity {
	KeyboardManager keyboardManager = GameScreen.keyboardManager;

	public abstract PlayerType getPlayerType();

	public void setPosition(double x, double y) {
		setX(getX() - (getSize() / 2));
		setY(getY() - (getSize() / 2));
	};

	public void update() {
		move();
		updateAttacks();
		checkBounds();
	}

	public void move() {
		if (keyboardManager.isKeyUp) {
			setY(getY() - getSpeed());
		}

		if (keyboardManager.isKeyDown) {
			setY(getY() + getSpeed());
		}

		if (keyboardManager.isKeyLeft) {
			setX(getX() - getSpeed());
		}

		if (keyboardManager.isKeyRight) {
			setX(getX() + getSpeed());
		}

		if (keyboardManager.isKeySpace) {
			attack();
		}
	}

	public void checkBounds() {
		if (getY() > GameScreen.gameHeight - getSize()) {
			setY(GameScreen.gameHeight - getSize());
		} else if (getY() < 0) {
			setY(0);
		}

		if (getX() > GameScreen.gameWidth - getSize()) {
			setX(0);;
		} else if (getX() < 0) {
			setX(GameScreen.gameWidth - getSize());
		}
	}
}
