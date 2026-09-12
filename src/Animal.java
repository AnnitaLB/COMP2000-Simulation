public abstract class Animal extends Entity {

    protected int energy;

    public Animal(int x, int y, int energy) {
        super(x, y);
        this.energy = energy;
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
    public void moveTowards(int targetX, int targetY){
       int newX = x;
    int newY = y;

    if (x < targetX) {
        newX = x + 10;
    } else if (x > targetX) {
        newX = x - 10;
    }

    if (y < targetY) {
        newY = y + 10;
    } else if (y > targetY) {
        newY = y - 10;
    }

    try {
        setPosition(newX, newY);
    } catch (InvalidPositionException e) {
        System.out.println(e.getMessage());
    }
}
    public abstract void move();
}