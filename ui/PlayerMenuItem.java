package ui;

import game.GameScreen;
import player.Player;
import java.awt.Graphics2D;

public class PlayerMenuItem {
    Player player;
    
    public PlayerMenuItem(Player player) {
        this.player = player;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(player.getSprite(), (int)(GameScreen.gameWidth / 2), (int)(GameScreen.gameHeight / 2),  (int)player.getWidth(), (int)player.getHeight(), null);
    }

    public void update() {
        
    }
}
