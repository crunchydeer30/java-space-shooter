package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import levels.Level;


public class GameScreen extends JPanel implements Runnable {
	private Thread gameThread;
	public static int gameWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static int gameHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static int FPS = 60;

	public static KeyboardManager keyboardManager = new KeyboardManager();
	public static SoundManager soundManager = new SoundManager();
	public static StateManager stateManager = new StateManager();
	public static LevelManager levelManager = new LevelManager();
	public static BackgroundManager backgroundManager = new BackgroundManager();
	public static ArrayList<Level> levelList;

	public GameScreen() {
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyboardManager);
		this.setFocusable(true);
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
	}

	public void startGameThread() {
		playMusic(0);
		stateManager.setGameState(GameState.TITLESCREEN);
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null) {
			Toolkit.getDefaultToolkit().sync();
			
			update();
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();

				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		stateManager.update();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		stateManager.draw(g2);
		
	}

	public void playMusic(int i) {
		soundManager.loop(i);
	}

	public static void playSoundEffect(int i) {
		soundManager.play(i);
	}
}
