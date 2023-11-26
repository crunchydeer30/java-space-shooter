package game;

import java.util.ArrayList;

import levels.Level;
import levels.Level1;
import levels.Level2;
import levels.Level3;

public class LevelManager {
    public Level currentLevel;
    public ArrayList<Level> levelList = new ArrayList<Level>();

    public void init() {
        levelList.add(new Level1());
        levelList.add(new Level2());
        levelList.add(new Level3());
        currentLevel = levelList.get(0);
        currentLevel.init();
    }

    public void update() {
        if (currentLevel.isCompleted() == true) {
            int curIdx = levelList.indexOf(currentLevel);

            if (curIdx == levelList.size() - 1) {
                init();
                GameScreen.stateManager.setGameState(GameState.MENU);
            } else {
                Level prevLevel = currentLevel;
                Level nextLevel = levelList.get(curIdx + 1);
                nextLevel.init();
                currentLevel = nextLevel;
                levelList.remove(prevLevel);
            }
        }
    }
}
