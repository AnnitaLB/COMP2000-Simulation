public abstract class Animal extends Entity {

    protected int energy;

    public Animal(int x, int y, int energy) {
        super(x, y);
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public abstract void move();
}