public class AxisMovementStrategy implements MovementStrategy {

    @Override
    public void move(Animal animal, int targetX, int targetY, int speed) {
        int newX = animal.getX();
        int newY = animal.getY();

        // Move along the X axis first
        if (newX != targetX) {
            if (newX < targetX) {
                newX += speed;
            } else {
                newX -= speed;
            }
        } else {
            // Move along the Y axis
            if (newY < targetY) {
                newY += speed;
            } else if (newY > targetY) {
                newY -= speed;
            }
        }

        try {
            animal.setPosition(newX, newY);
        } catch (InvalidPositionException e) {
            System.out.println(e.getMessage());
        }
    }
}