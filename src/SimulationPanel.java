import javax.swing.*;
import java.awt.*;

public class SimulationPanel extends JPanel {
    
public SimulationPanel() {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.fillRect(100, 100, 20, 20);

        g.setColor(Color.GRAY);
        g.fillOval(200, 200, 20, 20);

        g.setColor(Color.RED);
        g.fillOval(300, 300, 20, 20);
    }
}