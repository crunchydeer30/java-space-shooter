package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import levels.Level;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import ui.Menu;

public class GameScreen extends JPanel implements Runnable {
	private Thread gameThread;
	public static int gameWidth = 1366;
	public static int gameHeight = 768;
	public static int FPS = 60;
	public int gameState = 0;

	public static KeyboardManager keyboardManager = new KeyboardManager();
	Background background = new Background(gameWidth, gameHeight);

	ArrayList<Level> levelList = new ArrayList<Level>();
	public static Level currentLevel;
	public Menu mainMenu = new Menu();
	static SoundManager soundManager = new SoundManager();

	public GameScreen() {
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyboardManager);
		this.setFocusable(true);
	}

	public void startGameThread() {
		levelList.add(new Level1());
		levelList.add(new Level2());
		levelList.add(new Level3());
		currentLevel = levelList.get(0);
		currentLevel.init();
		playMusic();

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null) {
			update();
			repaint();

			if (currentLevel.isCompleted() == true) {
				int curIdx = levelList.indexOf(currentLevel);

				if (curIdx == levelList.size() - 1) {
					gameState = 1;
				} else {
					Level prevLevel = currentLevel;
					Level nextLevel = levelList.get(curIdx + 1);
					nextLevel.init();
					currentLevel = nextLevel;
					levelList.remove(prevLevel);
				}
			}

			Toolkit.getDefaultToolkit().sync();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();

				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				// System.out.println((int)(1000 / remainingTime));
				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		if (gameState == 1) {

		} else {
			background.update();
			currentLevel.update();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		if (gameState == 1) {
			mainMenu.draw(g2);
		} else {
			background.draw(g2);
			currentLevel.draw(g2);
		}
	}

	public void playMusic() {
		soundManager.loop();
	}

	public static void playSoundEffect() {
		soundManager.play();
	}
}
