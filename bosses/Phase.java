package bosses;

public abstract class Phase {
  Boss boss;

  public abstract Boss getBoss();

  public abstract void shoot();

  public abstract void move();
}
