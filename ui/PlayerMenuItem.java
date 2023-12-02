package ui;

import game.GameScreen;
import player.Player;
import player.PlayerType;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

public class PlayerMenuItem {
    Player player;
    double maxSpeed;
    double maxHP;
    double maxRateOfFire;
    double maxDamage;
    PlayerType type;

    public PlayerMenuItem(Player player) {
        this.player = player;
        ArrayList<Player> playerModels = GameScreen.playerManager.playerModelList;
        maxSpeed = playerModels.stream().max(Comparator.comparing(v -> v.getSpeed())).get().getSpeed();
        maxHP = playerModels.stream().max(Comparator.comparing(v -> v.getMaxHP())).get().getMaxHP();
        maxRateOfFire = playerModels.stream().max(Comparator.comparing(v -> v.getRateOfFire())).get().getRateOfFire();
        maxDamage = playerModels.stream().max(Comparator.comparing(v -> v.getDamage())).get().getDamage();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(player.getSprite(), (int) (GameScreen.gameWidth * 0.25),
                (int) ((GameScreen.gameHeight / 2) -player.getHeight()),
                (int) player.getWidth() * 2, (int) player.getHeight() * 2, null);

        double speed = player.getSpeed() / maxSpeed;
        double health = player.getMaxHP() / maxHP;
        double rateOfFire = player.getRateOfFire() / maxRateOfFire;
        double damage = player.getDamage() / maxDamage;

        drawParam(g2, "Speed", speed, GameScreen.gameHeight * 0.25);
        drawParam(g2, "Health", health, GameScreen.gameHeight * 0.35);
        drawParam(g2, "Rate of Fire", rateOfFire, GameScreen.gameHeight * 0.45);
        drawParam(g2, "Damage", damage, GameScreen.gameHeight * 0.55);
    }

    public void update() {

    }

    public void drawParam(Graphics2D g2, String name, double value, double y) {
        double x = GameScreen.gameWidth * 0.5;
        double width = GameScreen.gameWidth * 0.2;
        double height = 20;

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 22));
        g2.drawString(name, (int) x, (int) y);
        g2.drawRect((int)x, (int)y + 22, (int)width, (int)height);

        if ((width * value) < 0.3) {
            g2.setColor(Color.RED);
        } else if ((width * value) < 0.5) {
            g2.setColor(Color.ORANGE);
        } else if ((width * value) < 0.8) {
            g2.setColor(Color.YELLOW);
        } else {
            g2.setColor(Color.GREEN);
        }

        g2.fillRect((int)x, (int)y + 22, (int) (width * value), (int)height);
    }
}
