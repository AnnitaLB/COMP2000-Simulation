public class Entity {

    protected int x;
    protected int y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void setPosition(int newX, int newY) throws InvalidPositionException {
    if (newX < 0 || newX > 650 || newY < 0 || newY > 650) {
        throw new InvalidPositionException("Position is outside the simulation area.");
    }

    x = newX;
    y = newY;
}
}