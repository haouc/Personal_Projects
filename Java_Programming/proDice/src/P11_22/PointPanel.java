package P11_22;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hao on 11/1/2014.
 * This program is for drawing points and lining them up to a triangle.
 */
public class PointPanel extends JPanel{
    List pointList;

    public PointPanel(){
        pointList = new ArrayList();
        addMouseListener(new PointLocater(this));
        setBackground(Color.white);
    }

    //Drawing points stored in list.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));
        Ellipse2D e;
        for (int j = 0; j < pointList.size(); j++) {
            e = (Ellipse2D) pointList.get(j);
            g2D.setPaint(Color.BLACK);
            g2D.fill(e);
        }
        for(int k = 0; k < pointList.size(); k ++) {

            // drawing the side of triangle between vertex i and i - 1. Skip the next drawing when finishing a triangle to get disconnected triangles..
            if(k > 0 && k % 3 != 0){
                Ellipse2D e1 = (Ellipse2D) pointList.get(k);
                Ellipse2D e2 = (Ellipse2D) pointList.get(k - 1);
                //System.out.println((int)e1.getCenterX() + ", " + (int)e1.getCenterY() + ", " + (int)e2.getCenterX() + ", " + (int)e2.getCenterY());
                g2D.drawLine((int) e1.getCenterX(), (int) e1.getCenterY(), (int) e2.getCenterX(), (int) e2.getCenterY());
                g2D.setPaint(Color.BLUE);
            }

            // drawing the side of triangle vertex i and i - 2.
            if (k > 1 && (k + 1) % 3 == 0) {
                Ellipse2D e3 = (Ellipse2D) pointList.get(k);
                Ellipse2D e4 = (Ellipse2D) pointList.get(k - 2);
            //System.out.println("CP-2 " + (int)e3.getCenterX() + ", " + (int)e3.getCenterY() + ", " + (int)e4.getCenterX() + ", " + (int)e4.getCenterY());
                g2D.drawLine((int) e3.getCenterX(), (int) e3.getCenterY(), (int) e4.getCenterX(), (int) e4.getCenterY());
                g2D.setPaint(Color.BLUE);
            }
        }
    }

        public void addPoint(Point point){
            Ellipse2D ePoint = new Ellipse2D.Double(point.x, point.y, 5, 5);
            pointList.add(ePoint);
            repaint();
        }
}
