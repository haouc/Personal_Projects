package P10_35;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Hao on 10/31/2014.
 * this is a program to print restaurant customer's orders and balance to check out.
 */
public class RestaurantBillViewer {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame frame = new RestaurantBillFrame();
        frame.setTitle("Restaurant Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
