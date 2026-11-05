public class HungryRabbitState implements RabbitState {

    @Override
    public void update(Rabbit rabbit) {
        if (rabbit.getEnergy() >= 30) {
            rabbit.setState(new FedRabbitState());
        }
    }

    @Override
    public int getMovementSpeed() {
        return 12;
    }

    @Override
    public String getStateName() {
        return "Hungry";
    }
}