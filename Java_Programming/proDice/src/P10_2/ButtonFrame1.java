package P10_2;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Created by Hao on 10/29/2014.
 */
public class ButtonFrame1 extends JFrame{
    private static final int FRAME_WIDTH = 100;
    private static final int FRAME_HEIGHT = 60;

    public ButtonFrame1(){
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    private void createComponents(){
        JButton button = new JButton("Click me!");
        JPanel panel = new JPanel();
        panel.add(button);
        add(panel);

        ActionListener listener = new ClickListener();
        button.addActionListener(listener);
    }
}
