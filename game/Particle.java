package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Particle {

    public double liveTime;
    public Color color;
    public double size;
    public double x;
    public double y;
    public double angle;
    public double speed; 

    public Particle(double x, double y, double size, double angle, double speed, Color color) {
        this.color = color;
        this.angle = angle;
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
    }

    public void update() {
        x += Math.cos(Math.toRadians(angle)) * speed;
        y += Math.sin(Math.toRadians(angle)) * speed;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillRect((int) x, (int) y, (int) size, (int) size);
    }
}