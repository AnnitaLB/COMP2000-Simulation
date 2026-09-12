public class Wolf extends Animal {

    public Wolf(int x, int y) {
        super(x, y, 15);
    }

    @Override
    public void move() {
        x = x - 1;
    }
}