public class DiagonalMovementStrategy implements MovementStrategy {

    @Override
    public void move(Animal animal, int targetX, int targetY, int speed) {
        int newX = animal.getX();
        int newY = animal.getY();

        if (newX < targetX) {
            newX += speed;
        } else if (newX > targetX) {
            newX -= speed;
        }

        if (newY < targetY) {
            newY += speed;
        } else if (newY > targetY) {
            newY -= speed;
        }

        try {
            animal.setPosition(newX, newY);
        } catch (InvalidPositionException e) {
            System.out.println(e.getMessage());
        }
    }
}