public class AxisMovementStrategy implements MovementStrategy {

    @Override
    public void move(Animal animal, int targetX, int targetY, int speed) {
        int newX = animal.getX();
        int newY = animal.getY();

        // Move along the X axis first
        if (newX != targetX) {
            int distance = targetX - newX;

            if (Math.abs(distance) <= speed) {
                newX = targetX;
            } else {
                newX += Integer.signum(distance) * speed;
            }
        } else if (newY != targetY) {
            // Move along the Y axis after reaching targetX
            int distance = targetY - newY;

            if (Math.abs(distance) <= speed) {
                newY = targetY;
            } else {
                newY += Integer.signum(distance) * speed;
            }
        }

        try {
            animal.setPosition(newX, newY);
        } catch (InvalidPositionException e) {
            System.out.println(e.getMessage());
        }
    }
}