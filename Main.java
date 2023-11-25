import java.awt.BorderLayout;

import javax.swing.*;

import game.GameScreen;

public class Main{
	public static void main(String[] args)
	{
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Space Shooter");
		window.setLayout(new BorderLayout());
		
		GameScreen gameScreen = new GameScreen();	
		window.add(gameScreen);

		window.setSize(1366, 768);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gameScreen.startGameThread();
	}
}
