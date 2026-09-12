public class Rabbit extends Animal {

    public Rabbit(int x, int y) {
        super(x, y, 10);
    }

    @Override
    public void move() {
        x = x + 1;
    }
}