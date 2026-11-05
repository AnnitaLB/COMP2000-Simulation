public interface RabbitState {
    void update(Rabbit rabbit);
    int getMovementSpeed();
    String getStateName();
}