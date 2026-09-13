import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationPanel extends JPanel {

    private Rabbit rabbit;
    private Wolf wolf;

    private ArrayList<Grass> grassList;
    private ArrayList<Animal> animals;
    private ArrayList<Rabbit> rabbits;

    private Timer timer;
    private int energyCounter = 0;

    public SimulationPanel() {
        setBackground(Color.WHITE);

        rabbit = new Rabbit(200, 200);
        wolf = new Wolf(300, 300);

        rabbits = new ArrayList<Rabbit>();
        rabbits.add(rabbit);

        animals = new ArrayList<Animal>();

        animals.add(rabbit);
        animals.add(wolf);

        grassList = new ArrayList<Grass>();

        grassList.add(new Grass(100, 100));
        grassList.add(new Grass(400, 150));
        grassList.add(new Grass(250, 500));

        timer = new Timer(500, e -> {
        for (Rabbit currentRabbit : rabbits) {
            Grass targetGrass = findClosestGrass(currentRabbit);

            currentRabbit.moveTowards(
                targetGrass.getX(),
                targetGrass.getY(),
                10
            );
}
           Rabbit targetRabbit = findClosestRabbit();

            wolf.moveTowards(
            targetRabbit.getX(),
            targetRabbit.getY(),
            5
    );
            energyCounter = energyCounter + 1;

        if (energyCounter >= 3) {
            for (Animal animal : animals) {
                animal.loseEnergy(1);
            }

            energyCounter = 0;
}

            checkWolfRabbit();
            checkRabbitGrass();
            reproduceRabbits();
            checkEnergy();
            
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);

        for (Grass grass : grassList) {
        g.fillRect(grass.getX(), grass.getY(), 20, 20);
    }

        g.setColor(Color.GRAY);

        for (Rabbit currentRabbit : rabbits) {
            g.fillOval(
            currentRabbit.getX(),
            currentRabbit.getY(),
            20,
            20
            );
        }
        g.setColor(Color.RED);
        g.fillOval(wolf.getX(), wolf.getY(), 20, 20);

        g.setColor(Color.BLACK);

        g.drawString("Rabbit Energy: " + rabbits.get(0).getEnergy(), 10, 20);
        g.drawString("Wolf Energy: " + wolf.getEnergy(), 10, 40);
    }
    
    private void checkRabbitGrass() {
     for (Rabbit currentRabbit : rabbits) {

        for (int i = 0; i < grassList.size(); i++) {
            Grass grass = grassList.get(i);

            if (currentRabbit.getX() < grass.getX() + 20 &&
                currentRabbit.getX() + 20 > grass.getX() &&
                currentRabbit.getY() < grass.getY() + 20 &&
                currentRabbit.getY() + 20 > grass.getY()) {

                currentRabbit.addEnergy(10);

                int newX = (int)(Math.random() * 630);
                int newY = (int)(Math.random() * 630);

                grassList.set(i, new Grass(newX, newY));
            }
        }
    }
}
    private void checkWolfRabbit() {
        Rabbit targetRabbit = findClosestRabbit();

        if (wolf.getX() <= rabbit.getX() + 20 &&
        wolf.getX() + 20 >= rabbit.getX() &&
        wolf.getY() <= rabbit.getY() + 20 &&
        wolf.getY() + 20 >= rabbit.getY()) {

        wolf.addEnergy(20);

        int newX = (int)(Math.random() * 630);
        int newY = (int)(Math.random() * 630);

        try {
            targetRabbit.setPosition(newX, newY);
        } catch (InvalidPositionException e) {
            System.out.println(e.getMessage());
        }
    }
}
        private void checkEnergy() {
     for (int i = rabbits.size() - 1; i >= 0; i--) {
        Rabbit currentRabbit = rabbits.get(i);

        if (!currentRabbit.isAlive()) {
            rabbits.remove(i);
            animals.remove(currentRabbit);
        }
    }

    if (rabbits.size() == 0) {
        Rabbit newRabbit = new Rabbit(200, 200);

        rabbits.add(newRabbit);
        animals.add(newRabbit);

        rabbit = newRabbit;
    }

    if (!wolf.isAlive()) {
        animals.remove(wolf);

        int newX = (int)(Math.random() * 630);
        int newY = (int)(Math.random() * 630);

        wolf = new Wolf(newX, newY);
        animals.add(wolf);
    }


    if (!wolf.isAlive()) {
        int newX = (int)(Math.random() * 630);
        int newY = (int)(Math.random() * 630);

        wolf = new Wolf(newX, newY);
        animals.set(1, wolf);
    }
    }
    private Grass findClosestGrass(Rabbit currentRabbit) {
    Grass closestGrass = grassList.get(0);

    int closestDistance =
        Math.abs(currentRabbit.getX() - closestGrass.getX()) +
        Math.abs(currentRabbit.getY() - closestGrass.getY());

    for (int i = 1; i < grassList.size(); i++) {
        Grass grass = grassList.get(i);

        int distance =
            Math.abs(currentRabbit.getX() - grass.getX()) +
            Math.abs(currentRabbit.getY() - grass.getY());

        if (distance < closestDistance) {
            closestDistance = distance;
            closestGrass = grass;
        }
    }

    return closestGrass;
}
   private Rabbit findClosestRabbit() {
    Rabbit closestRabbit = rabbits.get(0);

    int closestDistance =
        Math.abs(wolf.getX() - closestRabbit.getX()) +
        Math.abs(wolf.getY() - closestRabbit.getY());

    for (int i = 1; i < rabbits.size(); i++) {
        Rabbit currentRabbit = rabbits.get(i);

        int distance =
            Math.abs(wolf.getX() - currentRabbit.getX()) +
            Math.abs(wolf.getY() - currentRabbit.getY());

        if (distance < closestDistance) {
            closestDistance = distance;
            closestRabbit = currentRabbit;
        }
    }

    return closestRabbit;
} 
private void reproduceRabbits() {
    int currentRabbitCount = rabbits.size();

    for (int i = 0; i < currentRabbitCount; i++) {
        Rabbit currentRabbit = rabbits.get(i);

        if (currentRabbit.getEnergy() >= 40 && rabbits.size() < 10) {
            currentRabbit.loseEnergy(15);

            Rabbit newRabbit = new Rabbit(
                currentRabbit.getX(),
                currentRabbit.getY()
            );

            rabbits.add(newRabbit);
            animals.add(newRabbit);
        }
    }
}
}