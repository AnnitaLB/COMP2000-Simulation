public abstract class Animal extends Entity {

    protected int energy;
    private MovementStrategy movementStrategy;

    public Animal(int x, int y, int energy) {
    super(x, y);
    this.energy = energy;
    this.movementStrategy = new DiagonalMovementStrategy();
}

    public int getEnergy() {
        return energy;
    }
    public void addEnergy(int amount) {
        energy = energy + amount;
    }
    public void loseEnergy(int amount) {
    energy = energy - amount;
    }
    public boolean isAlive() {
    return energy > 0;
    }
    public void setMovementStrategy(MovementStrategy strategy) {
    this.movementStrategy = strategy;
}

public void moveTowards(int targetX, int targetY, int speed) {
    movementStrategy.move(this, targetX, targetY, speed);
}
    public abstract void move();
}