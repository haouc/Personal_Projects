package pig;

import javax.swing.*;

/**
 * Created by Hao on 10/31/2014.
 * This is a pig game to animate the dice rolling by user and computer.
 */
public class PigViewer {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        JFrame frame = new PigFrame();
        frame.setTitle("Pig Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
