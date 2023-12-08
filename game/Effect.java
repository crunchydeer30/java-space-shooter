package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Effect {
    public double x;
    public double y;
    public double max_size;
    public Color color;
    public double particlesCount;
    public double speed;
    public ArrayList<Particle> particles = new ArrayList<Particle>();
    public double liveTime;
    public double timer;

    public Effect(double x, double y, double max_size, double speed, int particlesCount, double liveTime, Color color) {
        this.x = x;
        this.y = y;
        this.max_size = max_size;
        this.speed = speed;
        this.color = color;
        this.liveTime = liveTime;
        this.particlesCount = particlesCount;
        createParticles();
    }

    private void createParticles() {
        Random rand = new Random();

        for (int i = 1; i <= particlesCount; i++) {
            double particleAngle = rand.nextDouble(361);
            double particleSize = rand.nextDouble(0, max_size) + 1;
            particles.add(new Particle(x, y, particleSize, particleAngle, speed, color));
        }
    }

    public void draw(Graphics2D g2) {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).draw(g2);
        }
    }

    public void update() {
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }

        if (timer >= liveTime) {
            GameScreen.levelManager.currentLevel.getEffects().remove(this);
        }
        timer++;
    }
}