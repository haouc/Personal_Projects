/**
 * Created by Hao on 11/16/2014.
 */
public class CardDeck {

    private final int DECK_SIZE = 52;
    private PlayCard[] cards;
    private int n = 0;
    private final int SET_DECK = 6;

    public CardDeck() {
        cards = new PlayCard[DECK_SIZE*SET_DECK];
        for(int i = 0; i < 6; i++){
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 13; k++) {
                    cards[n] = new PlayCard(n, k, j + "" + k + ".png");
                    n++;
                }
            }
        }

    }


    public PlayCard deal() {
        return cards[--n];
    }

    public boolean isEmpty() {
        return (n == 0);
    }

    public int size() {
        return n;
    }

    public void cardShuffle() {
        for(int i = 0; i < 312; i++){
            int RandomCounterOne = (int)Math.random()*312;
            int RandomCounterTwo = (int)Math.random()*312;

            PlayCard tempCard = cards[RandomCounterOne];
            cards[RandomCounterOne] = cards[RandomCounterTwo];
            cards[RandomCounterTwo] = tempCard;
        }

    }
}
