public class Rabbit extends Animal {

    public Rabbit(int x, int y) {
        super(x, y, 30);
    }

    @Override
    public void move() {
        int direction = (int)(Math.random() * 4);

        if (direction == 0) {
            x = x + 10;
        } else if (direction == 1) {
            x = x - 10;
        } else if (direction == 2) {
            y = y + 10;
        } else {
            y = y - 10;
        }

        if (x < 0) {
            x = 0;
        }

        if (x > 650) {
            x = 650;
        }

        if (y < 0) {
            y = 0;
        }

        if (y > 650) {
            y = 650;
        }
    }
      
}