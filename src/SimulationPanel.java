import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {

    private Rabbit rabbit;
    private Wolf wolf;
    private Grass grass;

    public SimulationPanel() {
        setBackground(Color.WHITE);

        rabbit = new Rabbit(200, 200);
        wolf = new Wolf(300, 300);
        grass = new Grass(100, 100);
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
}