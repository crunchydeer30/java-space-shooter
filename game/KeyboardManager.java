package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardManager implements KeyListener {

    public boolean isKeyLeft;
    public boolean isKeyRight;
    public boolean isKeyUp;
    public boolean isKeyDown;
    public boolean isKeySpace;
    public boolean isKeyEnter;
    public boolean isKeyEscape;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            isKeyUp = true;
        }

        if (keyCode == KeyEvent.VK_S) {
            isKeyDown = true;
        }

        if (keyCode == KeyEvent.VK_A) {
            isKeyLeft = true;
        }

        if (keyCode == KeyEvent.VK_D) {
            isKeyRight = true;
        }

        if (keyCode == KeyEvent.VK_SPACE) {
            isKeySpace = true;
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            isKeyEnter = true;
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            isKeyEscape = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            isKeyUp = false;
        }

        if (keyCode == KeyEvent.VK_S) {
            isKeyDown = false;
        }

        if (keyCode == KeyEvent.VK_A) {
            isKeyLeft = false;
        }

        if (keyCode == KeyEvent.VK_D) {
            isKeyRight = false;
        }

        if (keyCode == KeyEvent.VK_SPACE) {
            isKeySpace = false;
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            isKeyEnter = false;
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            isKeyEscape = false;
        }
    }
}