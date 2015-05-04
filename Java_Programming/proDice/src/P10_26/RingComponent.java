package P10_26;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.*;

/**
 * Created by Hao on 10/30/2014.
 */
public class RingComponent extends JComponent {
    public void paintComponent(Graphics graph){
       //super.paintComponent(graph);

        Graphics2D g = (Graphics2D) graph;
        g.setStroke(new BasicStroke(5F));

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 450, 275);


        g.setColor(Color.BLUE);
        g.drawOval(50, 50, 100, 100);

        g.setColor(Color.BLACK);
        g.drawOval(165, 50, 100, 100);

        g.setColor(Color.RED);
        g.drawOval(280, 50, 100, 100);

        g.setColor(Color.YELLOW);
        g.drawOval(105, 100, 100, 100);

        g.setColor(Color.GREEN);
        g.drawOval(225, 100, 100, 100);
    }
}
