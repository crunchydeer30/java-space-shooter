package game;

public class HP {
    public double getMax_HP() {
        return max_HP;
    }

    public void setMax_HP(double max_HP) {
        this.max_HP = max_HP;
    }

    public double getCurrent_HP() {
        return current_HP;
    }

    public void setCurrent_HP(double current_HP) {
        this.current_HP = current_HP;
    }

    public HP(double max_HP, double current_HP) {
        this.max_HP = max_HP;
        this.current_HP = current_HP;
    }

    private double max_HP;
    private double current_HP;
}
