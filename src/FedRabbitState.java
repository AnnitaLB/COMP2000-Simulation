
public class FedRabbitState implements RabbitState {

    @Override
    public void update(Rabbit rabbit) {
        if (rabbit.getEnergy() < 30) {
            rabbit.setState(new HungryRabbitState());
        }
    }

    @Override
    public int getMovementSpeed() {
        return 8;
    }

    @Override
    public String getStateName() {
        return "Fed";
    }
}
