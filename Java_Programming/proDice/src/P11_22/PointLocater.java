package P11_22;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Hao on 11/1/2014.
 */
public class PointLocater extends MouseAdapter{
        PointPanel pointPanel;

        public PointLocater(PointPanel pp)
        {
            pointPanel = pp;
        }

        public void mousePressed(MouseEvent e)
        {
            Point p = e.getPoint();
            pointPanel.addPoint(p);
        }

}
