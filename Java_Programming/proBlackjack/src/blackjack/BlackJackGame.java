import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hao on 11/16/2014.
 */
public class BlackJackGame extends JFrame{

    private CardDeck myDeck;
    private JButton hit = new JButton("HIT");
    private JButton stay = new JButton("STAY");
    private JButton start = new JButton("START");

    public Player player = new Player("PLAYER", 1000);
    public Player dealer = new Player("DEALER", 10000);

    private JLabel infoBar = new JLabel(" ");

    JPanel tPanelOne = new JPanel(new GridLayout(1,2));
    JPanel tPanelTwo = new JPanel(new GridLayout(1,2));
    JPanel tPanelThree = new JPanel(new GridLayout(1,4));

    JPanel playerPanel = new JPanel();
    JPanel dealerPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    JPanel statusPanel = new JPanel();

    JTextArea playerLabel = new JTextArea(10,20);
    JTextArea dealerLabel = new JTextArea(10,20);


    public BlackJackGame() {
        JFrame frame = new JFrame("Black Jack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonsPanel.add(hit);
        buttonsPanel.add(stay);
        buttonsPanel.add(start);
        statusPanel.add(infoBar);

        hit.setEnabled(false);
        stay.setEnabled(false);

        hit.setEnabled(false);
        stay.setEnabled(false);

        dealerPanel.setBackground(Color.WHITE);
        playerPanel.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.lightGray);
        statusPanel.setBackground(Color.WHITE);

        playerLabel.setText("PLAYER");
        dealerLabel.setText("DEALER");
        playerLabel.setVisible(true);
        dealerLabel.setVisible(true);
        playerLabel.setEditable(false);
        dealerLabel.setEditable(false);
        playerLabel.setBackground(Color.WHITE);
        dealerLabel.setBackground(Color.GRAY);

        tPanelOne.add(dealerLabel);
        tPanelOne.add(dealerPanel);
        tPanelTwo.add(playerLabel);
        tPanelTwo.add(playerPanel);
        tPanelThree.add(buttonsPanel);
        tPanelThree.add(statusPanel);

        frame.setLayout(new BorderLayout());
        frame.add(tPanelOne, BorderLayout.NORTH);
        frame.add(tPanelTwo, BorderLayout.CENTER);
        frame.add(tPanelThree, BorderLayout.SOUTH);

        setListener();

        frame.repaint();
        frame.setSize(400, 300);
        frame.pack();
        frame.setVisible(true);
    }

    public void playerHit() {
        PlayCard newCard = player.deal(myDeck.deal());
        playerPanel.add(new JLabel(new ImageIcon("src/cards/" + newCard.toString())));
        playerPanel.updateUI();
    }

    public void dealerHitBackUp() {
        PlayCard newCrad = dealer.deal(myDeck.deal());
        dealerPanel.add(new JLabel(new ImageIcon("src/cards/backside.png")));
        dealerPanel.updateUI();
    }

    public void dealerHit() {
        PlayCard newCard = dealer.deal(myDeck.deal());
        dealerPanel.add(new JLabel(new ImageIcon("src/cards/" + newCard.toString())));
        dealerPanel.updateUI();
    }

    public void deal() {
        playerPanel.removeAll();
        dealerPanel.removeAll();
        playerPanel.updateUI();
        dealerPanel.updateUI();
        player.reset();
        dealer.reset();
        if (myDeck == null || myDeck.size() < 30) {
            myDeck = new CardDeck();
            myDeck.cardShuffle();
            infoBar.setText("Shuffling Cards");
        }
        playerHit();
        dealerHitBackUp();
        dealerHit();
    }

    public void checkWinner() {
        Bet myBet = new Bet(10);
        myBet.setBalance(1000);

        dealerPanel.removeAll();
        for (int i = 0; i < dealer.inHand(); i++) {
            dealerPanel.add(new JLabel(new ImageIcon("src/cards/" + dealer.cards[i].toString())));
        }
        if(player.value() > 21) {
            infoBar.setText("Player Busts!" + " Dealer wins " + myBet.getBet() + " Player has balance is " +
                    (player.getBalance() - myBet.getBet()));
            player.setBalance(player.getBalance() - myBet.getBet());
        }
        else if (dealer.value() > 21) {
            infoBar.setText("Dealer Busts!" + " Player wins " + myBet.getBet() + " Player has balance is " +
                    (player.getBalance() + myBet.getBet()));
            player.setBalance(player.getBalance() + myBet.getBet());
        }
        else if (dealer.value() == player.value()) {
            infoBar.setText("Dealer Wins!" + " Dealer wins " + myBet.getBet() + " Player has balance is " +
                    (player.getBalance() - myBet.getBet()));
            player.setBalance(player.getBalance() - myBet.getBet());
        }
        else if (dealer.value() < player.value()) {
            infoBar.setText("Player Wins!" + " Player wins " + myBet.getBet() + " Player has balance is " +
                    (player.getBalance() + myBet.getBet()));
            player.setBalance(player.getBalance() + myBet.getBet());
        }
        else {
            infoBar.setText("Dealer Wins!" + " Dealer wins " + myBet.getBet() + " Player has balance is " +
                    (player.getBalance() - myBet.getBet()));
            player.setBalance(player.getBalance() - myBet.getBet());
        }

        if(playerDone()) infoBar.setText("Play has lost all money! Game is over!");
    }
    public boolean playerDone() {
        return (player.getBalance() < 0);
    }


    public void setListener(){
        hit.addActionListener(new GameListener());
        stay.addActionListener(new GameListener());
        start.addActionListener(new GameListener());

    }

    public class GameListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == hit) {
            playerHit();
            if (player.value() > 21) {
                checkWinner();
                hit.setEnabled(false);
                stay.setEnabled(false);
                start.setEnabled(true);
            }
        }

        if (e.getSource() == stay) {
            while (dealer.value() < 17 || player.value() > dealer.value()) {
                dealerHit();
            }
            checkWinner();
            hit.setEnabled(false);
            stay.setEnabled(false);
            start.setEnabled(true);
        }

        if (e.getSource() == start) {
            deal();
            infoBar.setText(" ");
            hit.setEnabled(true);
            stay.setEnabled(true);
            start.setEnabled(false);
        }
    }

    }
}


