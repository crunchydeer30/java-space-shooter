package bosses;

import java.util.ArrayList;
import enemies.Enemy;

public abstract class Boss extends Enemy {
    public abstract Phase getCurrentPhase();
    public abstract ArrayList<Phase> getPhases();
}
