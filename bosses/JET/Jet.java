package bosses.JET;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import attacks.Attack;
import bosses.Boss;
import bosses.Phase;
import bosses.JET.phases.Phase1;
import bosses.JET.phases.Phase2;


public class Jet extends Boss {
  private Image sprite = new ImageIcon("assets/graphics/boss_jet.png").getImage();
    private double size = 256;
    private double width;
    private double height;
    private double x;
    private double y;
    private float speed = 1f;
    private double rateOfFire = 12.5f;
    private double maxHP = 5000;
    private double currentHP = maxHP;
    private double damage = 20;
    public ArrayList<Phase> phases = new ArrayList<Phase>();
    public ArrayList<Attack> attacks = new ArrayList<Attack>();
    public Phase currentPhase;

    public Jet() {
        calculateDimensions();
        phases.add(new Phase1(this));
        phases.add(new Phase2(this));
        currentPhase = phases.get(0);
    }

    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public ArrayList<Phase> getPhases() {
        return phases;
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public double getDamage() {
        return damage;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    public double getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(double currentHP) {
        this.currentHP = currentHP;
    }

    public double getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(double maxHP) {
        this.maxHP = maxHP;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSize() {
        return size;
    }

    public Image getSprite() {
        return this.sprite;
    }

    public double getSpeed() {
        return speed;
    }

    public double getRateOfFire() {
        return rateOfFire;
    }

    public void move() {
        currentPhase.move();
    }

    public void attack() {
        if (currentHP <= maxHP / 2) {
            currentPhase = phases.get(1);
        } else {
            currentPhase = phases.get(0);
        }
        currentPhase.shoot();
    }
}
