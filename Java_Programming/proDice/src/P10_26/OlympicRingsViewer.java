package P10_26;

import javax.swing.*;

/**
 * Created by Hao on 10/29/2014.
 */
public class OlympicRingsViewer {
    public static void main(String[] args){
        JFrame frame = new JFrame();

        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent component = new RingComponent();
        frame.add(component);
        frame.setVisible(true);
    }

}
