import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationPanel extends JPanel {

    private Rabbit rabbit;
    private Wolf wolf;

    private ArrayList<Grass> grassList;

    private Timer timer;

    public SimulationPanel() {
        setBackground(Color.WHITE);

        rabbit = new Rabbit(200, 200);
        wolf = new Wolf(300, 300);

        grassList = new ArrayList<Grass>();

        grassList.add(new Grass(100, 100));
        grassList.add(new Grass(400, 150));
        grassList.add(new Grass(250, 500));

        timer = new Timer(500, e -> {
            Grass targetGrass = findClosestGrass();

        rabbit.moveTowards(
            targetGrass.getX(),
            targetGrass.getY()
        );
            wolf.moveTowards(rabbit.getX(),rabbit.getY());

            rabbit.loseEnergy(1);
            wolf.loseEnergy(1);

            checkRabbitGrass();
            checkWolfRabbit();
            checkEnergy();
            
            repaint();
        });
        try {
            rabbit.setPosition(700, 700);
        } catch (InvalidPositionException e) {
            System.out.println(e.getMessage());
        }
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
        g.fillOval(rabbit.getX(), rabbit.getY(), 20, 20);

        g.setColor(Color.RED);
        g.fillOval(wolf.getX(), wolf.getY(), 20, 20);

        g.setColor(Color.BLACK);

        g.drawString("Rabbit Energy: " + rabbit.getEnergy(), 10, 20);
        g.drawString("Wolf Energy: " + wolf.getEnergy(), 10, 40);
    }
    
    private void checkRabbitGrass() {
    for (int i = 0; i < grassList.size(); i++) {

        Grass grass = grassList.get(i);

        if (rabbit.getX() < grass.getX() + 20 &&
            rabbit.getX() + 20 > grass.getX() &&
            rabbit.getY() < grass.getY() + 20 &&
            rabbit.getY() + 20 > grass.getY()) {

            rabbit.addEnergy(5);

            int newX = (int)(Math.random() * 630);
            int newY = (int)(Math.random() * 630);

            grassList.set(i, new Grass(newX, newY));
        }
    }
}
    private void checkWolfRabbit() {
        if (wolf.getX() < rabbit.getX() + 20 &&
            wolf.getX() + 20 > rabbit.getX() &&
            wolf.getY() < rabbit.getY() + 20 &&
            wolf.getY() + 20 > rabbit.getY()) {

        wolf.addEnergy(10);

        int newX = (int)(Math.random() * 630);
        int newY = (int)(Math.random() * 630);

        rabbit = new Rabbit(newX, newY);}
        }
        private void checkEnergy() {
    if (!rabbit.isAlive()) {
        int newX = (int)(Math.random() * 630);
        int newY = (int)(Math.random() * 630);

        rabbit = new Rabbit(newX, newY);
    }

    if (!wolf.isAlive()) {
        int newX = (int)(Math.random() * 630);
        int newY = (int)(Math.random() * 630);

        wolf = new Wolf(newX, newY);
    }
    }
    private Grass findClosestGrass() {
    Grass closestGrass = grassList.get(0);

    int closestDistance =
        Math.abs(rabbit.getX() - closestGrass.getX()) +
        Math.abs(rabbit.getY() - closestGrass.getY());

    for (int i = 1; i < grassList.size(); i++) {
        Grass grass = grassList.get(i);

        int distance =
            Math.abs(rabbit.getX() - grass.getX()) +
            Math.abs(rabbit.getY() - grass.getY());

        if (distance < closestDistance) {
            closestDistance = distance;
            closestGrass = grass;
        }
    }

    return closestGrass;
}
}