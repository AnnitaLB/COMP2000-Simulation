import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {

    private Rabbit rabbit;
    private Wolf wolf;
    private Grass grass;

    private Timer timer;

    public SimulationPanel() {
        setBackground(Color.WHITE);

        rabbit = new Rabbit(200, 200);
        wolf = new Wolf(300, 300);
        grass = new Grass(100, 100);

        timer = new Timer(500, e -> {
            rabbit.moveTowards(grass.getX(),grass.getY());
            wolf.move();

            checkRabbitGrass();

            repaint();
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.fillRect(grass.getX(), grass.getY(), 20, 20);

        g.setColor(Color.GRAY);
        g.fillOval(rabbit.getX(), rabbit.getY(), 20, 20);

        g.setColor(Color.RED);
        g.fillOval(wolf.getX(), wolf.getY(), 20, 20);
    }
    
    private void checkRabbitGrass(){
        if(rabbit.getX() < grass.getX() + 20 && rabbit.getX() + 20> grass.getX() &&
            rabbit.getY() < grass.getY() +20 && rabbit.getY() + 20 > grass.getY()) {

        rabbit.addEnergy(5);

        int newX = (int)(Math.random() * 630);
        int newY = (int)(Math.random() * 630);

        grass = new Grass(newX, newY);
    }
}
}