package P10_35;

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Hao on 10/30/2014.
 * This is a program to print customer's order and balance.
 */
public class RestaurantBillFrame extends JFrame {
    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 500;

    private static final double INITIAL_BALANCE = 0;
    private static final double TAX_RATE = 0.07;

    private JLabel orderLabel0;
    private JTextField orderField0;
    private JButton orderButton0;

    private JLabel orderLabel1;
    private JTextField orderField1;
    private JButton orderButton1;

    private JLabel orderLabel2;
    private JTextField orderField2;
    private JButton orderButton2;

    private JLabel orderLabel3;
    private JTextField orderField3;
    private JButton orderButton3;

    private JLabel orderLabel4;
    private JTextField orderField4;
    private JButton orderButton4;

    private JLabel orderLabel5;
    private JTextField orderField5;
    private JButton orderButton5;

    private JLabel orderLabel6;
    private JTextField orderField6;
    private JButton orderButton6;

    private JLabel orderLabel7;
    private JTextField orderField7;
    private JButton orderButton7;

    private JLabel orderLabel8;
    private JTextField orderField8;
    private JButton orderButton8;

    private JLabel orderLabel9;
    private JTextField orderField9;
    private JButton orderButton9;


    private JLabel orderLabel10;
    private JTextField orderField10;
    private JButton orderButton10;

    private JTextArea resultArea;
    private double balance;

    public RestaurantBillFrame(){
        balance = INITIAL_BALANCE;
        resultArea = new JTextArea(23, 23);
        resultArea.setText("--- Order Total Amount --- $" + "   " + balance + "\n");
        resultArea.setVisible(true);
        resultArea.setEditable(false);

        createTextField();
        createButton();
        createPanel();

        setSize(FRAME_WIDTH,FRAME_HEIGHT);
    }

    ArrayList<Menu> itemList = new ArrayList<Menu> (Arrays.asList(
        new Menu(" Sandwiches", 7.50),
        new Menu(" Little Goat", 15.00),
        new Menu(" Big Star", 13.50),
        new Menu(" Fat Rice", 12.00),
        new Menu(" XOCO", 10.00),
        new Menu(" HopLeaf", 12.00),
        new Menu(" Sunda", 12.00),
        new Menu(" Coalfire Pizza", 11.50),
        new Menu(" Smoque BBQ", 13.00),
        new Menu(" Eataly", 12.75)));

    private void createTextField(){
        orderLabel0 = new JLabel("Menu Items");
        final int FIELD_WIDTH = 10;
        orderField0 = new JTextField(FIELD_WIDTH);
        orderField0.setText(itemList.get(0).getItem()+ " $" + itemList.get(0).getPrice());

        orderLabel1 = new JLabel("Menu Items");
        orderField1 = new JTextField(FIELD_WIDTH);
        orderField1.setText(itemList.get(1).getItem()+ " $" + itemList.get(1).getPrice());

        orderLabel2 = new JLabel("Menu Items");
        orderField2 = new JTextField(FIELD_WIDTH);
        orderField2.setText(itemList.get(2).getItem()+ " $" + itemList.get(2).getPrice());

        orderLabel3 = new JLabel("Menu Items");
        orderField3 = new JTextField(FIELD_WIDTH);
        orderField3.setText(itemList.get(3).getItem()+ " $" + itemList.get(3).getPrice());

        orderLabel4 = new JLabel("Menu Items");
        orderField4 = new JTextField(FIELD_WIDTH);
        orderField4.setText(itemList.get(4).getItem()+ " $" + itemList.get(4).getPrice());

        orderLabel5 = new JLabel("Menu Items");
        orderField5 = new JTextField(FIELD_WIDTH);
        orderField5.setText(itemList.get(5).getItem()+ " $" + itemList.get(5).getPrice());

        orderLabel6 = new JLabel("Menu Items");
        orderField6 = new JTextField(FIELD_WIDTH);
        orderField6.setText(itemList.get(6).getItem()+ " $" + itemList.get(6).getPrice());

        orderLabel7 = new JLabel("Menu Items");
        orderField7 = new JTextField(FIELD_WIDTH);
        orderField7.setText(itemList.get(7).getItem()+ " $" + itemList.get(7).getPrice());

        orderLabel8 = new JLabel("Menu Items");
        orderField8 = new JTextField(FIELD_WIDTH);
        orderField8.setText(itemList.get(8).getItem()+ " $" + itemList.get(8).getPrice());

        orderLabel9 = new JLabel("Menu Items");
        orderField9 = new JTextField(FIELD_WIDTH);
        orderField9.setText(itemList.get(9).getItem()+ " $" + itemList.get(9).getPrice());

        orderLabel10 = new JLabel("Total Amount");
        orderField10 = new JTextField(FIELD_WIDTH);
        orderField10.setText(" Your Order Balance");

    }
    class AddOrderListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            DecimalFormat df = new DecimalFormat("#####.##");
            double price = 0.00;
            String item = "";
            if (event.getSource() == orderButton0) {price = 7.50; item = "Sandwiches";}
            if (event.getSource() == orderButton1) {price = 15.00; item = "Little Goat";}
            if (event.getSource() == orderButton2) {price = 13.50; item = "Big Star";}
            if (event.getSource() == orderButton3) {price = 12.00; item = "Fat Rice";}
            if (event.getSource() == orderButton4) {price = 10.00; item = "XOCO";}
            if (event.getSource() == orderButton5) {price = 12.00; item = "HopLeaf";}
            if (event.getSource() == orderButton6) {price = 12.00; item = "Sunda";}
            if (event.getSource() == orderButton7) {price = 11.50; item = "Coalfire Pizza";}
            if (event.getSource() == orderButton8) {price = 13.00; item = "Smoque BBQ";}
            if (event.getSource() == orderButton9) {price = 12.75; item = "Eataly";}
            if (event.getSource() == orderButton10) {
                item = "-------------------------------------------------" + "\n" + " Total Amount";
                resultArea.append(item + " $ " + balance + "\n" + " Plus TAX(7%) is " + "$ " + df.format(balance * 1.07)
                        + "\n" + " Tip(15%) is " + "$ " + df.format(balance * 1.07 * 0.15) + "\n" +
                " Balance after Tip is " + "$ " + df.format(balance * 1.07 * 1.15) + "\n");
                return;
            }

            balance = balance + price;
            resultArea.append(" " + item + " $" + price + "\n");
        }
    }


    private void createButton(){
        orderButton0 = new JButton("Add Order");
        orderButton1 = new JButton("Add Order");
        orderButton2 = new JButton("Add Order");
        orderButton3 = new JButton("Add Order");
        orderButton4 = new JButton("Add Order");
        orderButton5 = new JButton("Add Order");
        orderButton6 = new JButton("Add Order");
        orderButton7 = new JButton("Add Order");
        orderButton8 = new JButton("Add Order");
        orderButton9 = new JButton("Add Order");
        orderButton10 = new JButton("Total Order Balance");


        ActionListener listener = new AddOrderListener();
        orderButton0.addActionListener(listener);
        orderButton1.addActionListener(listener);
        orderButton2.addActionListener(listener);
        orderButton3.addActionListener(listener);
        orderButton4.addActionListener(listener);
        orderButton5.addActionListener(listener);
        orderButton6.addActionListener(listener);
        orderButton7.addActionListener(listener);
        orderButton8.addActionListener(listener);
        orderButton9.addActionListener(listener);
        orderButton10.addActionListener(listener);

    }

    private void createPanel(){

        JPanel buttonPanel = new JPanel(new GridLayout(11,3));

        buttonPanel.add(orderButton0);
        buttonPanel.add(orderLabel0);
        buttonPanel.add(orderField0);

        buttonPanel.add(orderButton1);
        buttonPanel.add(orderLabel1);
        buttonPanel.add(orderField1);

        buttonPanel.add(orderButton2);
        buttonPanel.add(orderLabel2);
        buttonPanel.add(orderField2);

        buttonPanel.add(orderButton3);
        buttonPanel.add(orderLabel3);
        buttonPanel.add(orderField3);

        buttonPanel.add(orderButton4);
        buttonPanel.add(orderLabel4);
        buttonPanel.add(orderField4);

        buttonPanel.add(orderButton5);
        buttonPanel.add(orderLabel5);
        buttonPanel.add(orderField5);

        buttonPanel.add(orderButton6);
        buttonPanel.add(orderLabel6);
        buttonPanel.add(orderField6);

        buttonPanel.add(orderButton7);
        buttonPanel.add(orderLabel7);
        buttonPanel.add(orderField7);

        buttonPanel.add(orderButton8);
        buttonPanel.add(orderLabel8);
        buttonPanel.add(orderField8);

        buttonPanel.add(orderButton9);
        buttonPanel.add(orderLabel9);
        buttonPanel.add(orderField9);

        buttonPanel.add(orderButton10);
        buttonPanel.add(orderLabel10);
        buttonPanel.add(orderField10);

        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(buttonPanel, BorderLayout.WEST);
        panel.add(scrollPane, BorderLayout.EAST);
        panel.setBackground(Color.LIGHT_GRAY);
        add(panel);

    }
}
