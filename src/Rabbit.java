public class Rabbit extends Animal {
    private RabbitState state;

    public Rabbit(int x, int y) {
    super(x, y, 40);
    setMovementStrategy(new AxisMovementStrategy());
    this.state = new FedRabbitState();
}
    public void setState(RabbitState state) {
    this.state = state;
}

    public int getMovementSpeed() {
    state.update(this);
    return state.getMovementSpeed();
}

    public String getStateName() {
    state.update(this);
    return state.getStateName();
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
    public void resetEnergy() {
    energy = 30;
}
      
}