package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.GameScreen;
import sounds.MusicPlayer;
import stateManager.GameStateType;

public class Menu {
    Graphics2D g2;
    public Image backgroundImage = new ImageIcon("assets/graphics/background_2.png").getImage();
    public ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
    public MenuItem selectedItem;
    public MusicPlayer menuMusic = new MusicPlayer("menu");

    public Menu() {
        // this.g2 = GameScreen.g2;
        Font font = new Font("Arial", Font.BOLD, 52);
        menuItems.add(new MenuItem("START", font, Color.WHITE, 0, (int) (GameScreen.gameHeight * 0.4)));
        menuItems.add(new MenuItem("OPTIONS", font, Color.WHITE, 0, (int) (GameScreen.gameHeight * 0.6)));
        menuItems.add(new MenuItem("EXIT", font, Color.WHITE, 0, (int) (GameScreen.gameHeight * 0.8)));
        selectedItem = menuItems.get(0);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.drawImage(backgroundImage, 0, 0, GameScreen.gameWidth, GameScreen.gameHeight, null);
        for (int i = 0; i < menuItems.size(); i++) {
            menuItems.get(i).draw(g2);
        }
    }

    public void update() {
        control();
        selectedItem.setIsSelected(true);
    }

    public void control() {
        if (GameScreen.keyboardManager.isKeyDown) {
            if (menuItems.indexOf(selectedItem) < menuItems.size() - 1) {
                selectedItem.setIsSelected(false);
                selectedItem = menuItems.get(menuItems.indexOf(selectedItem) + 1);
                GameScreen.keyboardManager.isKeyDown = false;
            }

        }

        if (GameScreen.keyboardManager.isKeyUp) {
            if (menuItems.indexOf(selectedItem) > 0) {
                selectedItem.setIsSelected(false);
                selectedItem = menuItems.get(menuItems.indexOf(selectedItem) - 1);
                GameScreen.keyboardManager.isKeyUp = false;
            }
        }

        if (GameScreen.keyboardManager.isKeyEnter) {
            if (menuItems.indexOf(selectedItem) == 0) {
                GameScreen.stateManager.setGameState(GameStateType.GAMEPLAY);
            } else if (menuItems.indexOf(selectedItem) == 1) {
                GameScreen.stateManager.setGameState(GameStateType.OPTIONS);
            } else if (menuItems.indexOf(selectedItem) == 2) {
                System.exit(0);
            }
            GameScreen.keyboardManager.isKeyEnter = false;
        }
    }
}
