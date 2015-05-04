package pig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hao on 10/30/2014.
 * This is a program to animate the pig game to let user roll dice contesting with computer.
 */
public class PigFrame extends JFrame {
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 500;
    private static final int INITIAL_BALANCE = 0;

    private JButton diceButton0;
    private JButton diceButton1;
    private JButton diceButton2;
    private JButton diceButton3;


    private JTextArea resultAreaOne;
    private JTextArea resultAreaTwo;
    private int balance;
    private int balanceC;

    public PigFrame(){
        balance = INITIAL_BALANCE;
        resultAreaOne = new JTextArea(20, 48);
        resultAreaOne.setText("Player Earned " + "   " + balance + "\n");
        resultAreaOne.setVisible(true);
        resultAreaOne.setEditable(false);
        resultAreaTwo = new JTextArea(20, 48);
        resultAreaTwo.setText("Computer Earned " + "   " + balance + "\n");
        resultAreaTwo.setVisible(true);
        resultAreaTwo.setEditable(false);

        createButton();
        createPanel();

        setSize(FRAME_WIDTH,FRAME_HEIGHT);
    }

    public int getValue(){
        int value = (int)((Math.random()*60)/10+1);
        return value;
    }

    class DiceRollListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            int value;
            int valueC;
            String item;
            String itemC;
            //Is there risk here to use getSource(reference on memory?) identifying button clicked?
            if (event.getSource() == diceButton0) {
                value = getValue();
                item = "Player Earned: ";
                if(value == 1){
                    item = "Player fails, earns zero, and Computer wins with ";
                    balance = 0;
                    resultAreaOne.append("Player rolled " + value + "\n" + item + balanceC + " points" + "\n");
                    return;
                }
                resultAreaOne.append(item + value + "\n");
                balance = balance + value;
            }
            if (event.getSource() == diceButton1) {
                valueC = 0;
                //Only set a relatively big random number for computer's turn, theoretically computer could encounter twice 1 if goes through all 12 rolls.
                //Maybe there is a better and more natural idea to animate randomly rolling by computer.

                for(int counter = 0; counter < getValue()*2; counter++){
                        itemC = "Computer earns: ";
                        valueC = getValue();
                        if(valueC == 1){
                            itemC = "Computer fails, earns zero, and Player wins with ";
                            balanceC = 0;
                            resultAreaTwo.append("Computer rolled " + valueC + "\n" + itemC + balance + " points" + "\n");
                            break;
                        }
                        resultAreaTwo.append(itemC + valueC + "\n");
                        balanceC = balanceC + valueC;
                }
            }

            if (event.getSource() == diceButton2) {
                if(balance > balanceC){
                    item = "-------------------------------------------------" + "\n" + "Player wins with total Earned Points: ";
                }
                else if (balance == balanceC){
                    item = "-------------------------------------------------" + "\n" + "Tie with total Earned Points: ";
                }
                else {
                    item = "-------------------------------------------------" + "\n" + "Computer wins with total Earned Points: ";
                }

                resultAreaOne.append(item + balance + "\n");
                return;

            }
            if(event.getSource() == diceButton3){
                resultAreaOne.setText(null);
                resultAreaTwo.setText(null);
                resultAreaOne.append("Please Click ROLL to Restart!" + "\n");
                resultAreaTwo.append("Computer is Waiting!" + "\n");
                balance = 0;
                balanceC = 0;
            }
        }
    }

    private void createButton(){
        diceButton0 = new JButton("ROLL");
        diceButton1 = new JButton("HOLD");
        diceButton2 = new JButton("FINISH");
        diceButton3 = new JButton("RESTART");

        ActionListener listener = new DiceRollListener();
        diceButton0.addActionListener(listener);
        diceButton1.addActionListener(listener);
        diceButton2.addActionListener(listener);
        diceButton3.addActionListener(listener);
    }

    private void createPanel(){

        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(4,1));
        JPanel resultPanel = new JPanel(new BorderLayout());

        buttonPanel.add(diceButton0);
        buttonPanel.add(diceButton1);
        buttonPanel.add(diceButton2);
        buttonPanel.add(diceButton3);

        JScrollPane scrollPaneOne = new JScrollPane(resultAreaOne);
        JScrollPane scrollPaneTwo = new JScrollPane(resultAreaTwo);
        resultPanel.add(scrollPaneOne, BorderLayout.WEST);
        resultPanel.add(scrollPaneTwo, BorderLayout.EAST);
        panel.add(buttonPanel, BorderLayout.WEST);
        panel.add(resultPanel, BorderLayout.EAST);
        add(panel);
    }
}
