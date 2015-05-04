/**
 * Created by Hao on 11/16/2014.
 */
public class Player {
    final int TOTAL_CARDS = 312;
    public PlayCard[] cards = new PlayCard[TOTAL_CARDS];
    private int n = 0;
    private String name;
    private int balance;


    public Player(String name, int myBalance) {
        this.name = name;
        this.balance = myBalance;
    }

    public int inHand() {
        return n;
    }

    public int getBalance(){
        return balance;
    }

    public void setBalance(int balance){
        this.balance =  balance;
    }

    public PlayCard deal(PlayCard c) {
        cards[n++] = c;
        return c;
    }


    public void reset() {
        n = 0;
    }

    public int value() {
        int cardValue = 0;
        boolean hasAce = false;
        for (int i = 0; i < n; i++) {
            cardValue = cardValue + cards[i].rank();
            if (cards[i].isAce()) {
                hasAce = true;
            }
        }
        if (hasAce && (cardValue <= 11)) {
            cardValue = cardValue + 10;
        }
        return cardValue;
    }

}
