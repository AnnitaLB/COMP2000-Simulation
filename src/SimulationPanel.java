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
        grassList.add(new Grass(500, 400));
        grassList.add(new Grass(150, 350));

        timer = new Timer(500, e -> {
        for (Rabbit currentRabbit : rabbits) {
            Grass targetGrass = findClosestGrass(currentRabbit);

            currentRabbit.moveTowards(
                targetGrass.getX(),
                targetGrass.getY(),
                currentRabbit.getMovementSpeed()
            );
}
        if (rabbits.size() > 0) {
            Rabbit targetRabbit = findClosestRabbit(wolf);

            wolf.moveTowards(
            targetRabbit.getX(),
            targetRabbit.getY(),
            11
    );
}
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

         for (int i = 0; i < rabbits.size(); i++) {
        Rabbit currentRabbit = rabbits.get(i);

        g.setColor(Color.GRAY);

        g.fillOval(
            currentRabbit.getX(),
            currentRabbit.getY(),
            20,
            20
        );

        g.setColor(Color.BLACK);

        g.drawString(
            "R" + (i + 1) + ": " + currentRabbit.getEnergy(),
            currentRabbit.getX(),
            currentRabbit.getY() - 5
        );
    }

    g.setColor(Color.RED);

    g.fillOval(
        wolf.getX(),
        wolf.getY(),
        20,
        20
    );

    g.setColor(Color.BLACK);

    g.drawString(
        "Rabbit Count: " + rabbits.size(),
        10,
        20
    );

    g.drawString(
        "Wolf Energy: " + wolf.getEnergy(),
        10,
        40
    );
    double averageRabbitEnergy = rabbits.stream()
    .mapToInt(Rabbit::getEnergy)
    .average()
    .orElse(0.0);

long hungryRabbitCount = rabbits.stream()
    .filter(r -> r.getEnergy() < 30)
    .count();

    g.drawString(
    String.format("Average Rabbit Energy: %.1f", averageRabbitEnergy),
    10, 60
);

    g.drawString(
    "Hungry Rabbits: " + hungryRabbitCount,
    10, 80
);

java.util.List<Animal> animalSnapshot =
    new java.util.ArrayList<>(animals);

java.util.Map<String, java.util.IntSummaryStatistics> energyBySpecies =
    animalSnapshot.parallelStream()
        .collect(java.util.stream.Collectors.groupingBy(
            animal -> animal.getClass().getSimpleName(),
            java.util.stream.Collectors.summarizingInt(
                Animal::getEnergy
            )
        ));


java.util.IntSummaryStatistics rabbitStats =
    energyBySpecies.get("Rabbit");

java.util.IntSummaryStatistics wolfStats =
    energyBySpecies.get("Wolf");

if (rabbitStats != null) {
    g.drawString(
        String.format(
            "Rabbits: %d | Energy: %d-%d",
            rabbitStats.getCount(),
            rabbitStats.getMin(),
            rabbitStats.getMax()
        ),
        10, 100
    );
}

if (wolfStats != null) {
    g.drawString(
        String.format(
            "Wolves: %d | Energy: %d-%d",
            wolfStats.getCount(),
            wolfStats.getMin(),
            wolfStats.getMax()
        ),
        10, 120
    );
}
}
    
    private void checkRabbitGrass() {
     for (Rabbit currentRabbit : rabbits) {

        for (int i = 0; i < grassList.size(); i++) {
            Grass grass = grassList.get(i);

            if (currentRabbit.getX() < grass.getX() + 20 &&
                currentRabbit.getX() + 20 > grass.getX() &&
                currentRabbit.getY() < grass.getY() + 20 &&
                currentRabbit.getY() + 20 > grass.getY()) {

                currentRabbit.addEnergy(20);

                int newX = (int)(Math.random() * 630);
                int newY = (int)(Math.random() * 630);

                grassList.set(i, new Grass(newX, newY));
            }
        }
    }
}
    private void checkWolfRabbit() {
        if (rabbits.size() == 0) {
        return;
    }

    Rabbit targetRabbit = findClosestRabbit(wolf);

    int xDistance = Math.abs(
        wolf.getX() - targetRabbit.getX()
    );

    int yDistance = Math.abs(
        wolf.getY() - targetRabbit.getY()
    );

    if (xDistance <= 20 && yDistance <= 20) {

        wolf.addEnergy(30);

        rabbits.remove(targetRabbit);
        animals.remove(targetRabbit);
    }
}

    private void checkEnergy() {
    java.util.List<Rabbit> deadRabbits = rabbits.stream()
    .filter(r -> !r.isAlive())
    .collect(java.util.stream.Collectors.toList());

    rabbits.removeAll(deadRabbits);
    animals.removeAll(deadRabbits);
        

    if (rabbits.size() == 0) {
        int newX = (int)(Math.random() * 630);
    int newY = (int)(Math.random() * 630);

    Rabbit newRabbit = new Rabbit(newX, newY);

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

private int distance(Entity a, Entity b) {
    return Math.abs(a.getX() - b.getX())
         + Math.abs(a.getY() - b.getY());
}

private Grass findClosestGrass(Rabbit currentRabbit) {
    return grassList.stream()
        .min(java.util.Comparator.comparingInt(
            grass -> distance(currentRabbit, grass)))
        .orElseThrow(() -> new IllegalStateException("No grass available"));
}

private Rabbit findClosestRabbit(Wolf currentWolf) {
    return rabbits.stream()
        .min(java.util.Comparator.comparingInt(
            rabbit -> distance(currentWolf, rabbit)))
        .orElseThrow(() -> new IllegalStateException("No rabbits available"));
}

private void reproduceRabbits() {
    int currentRabbitCount = rabbits.size();

    for (int i = 0; i < currentRabbitCount; i++) {
        Rabbit currentRabbit = rabbits.get(i);

        if (currentRabbit.getEnergy() >= 55 && rabbits.size() < 6) {
            currentRabbit.loseEnergy(15);

            int newX;
            int newY;

            do {
                newX = (int)(Math.random() * 630);
                newY = (int)(Math.random() * 630);
            } while (
                Math.abs(newX - wolf.getX()) < 100 &&
                Math.abs(newY - wolf.getY()) < 100
            );

            Rabbit newRabbit = new Rabbit(newX, newY);

            rabbits.add(newRabbit);
            animals.add(newRabbit);
        }
    }
}
}