/**
 * Created by Hao on 11/16/2014.
 */
public class PlayCard {
    private int cardNumber;
    private int rank;
    private String cardFace;

    PlayCard(int cardOrder, int rank, String face) {
        this.cardNumber = cardOrder;
        this.rank = rank;
        this.cardFace = face;
    }

    public boolean isAce() {
        return rank == 0;
    }

    public int rank() {
        if (rank == 0) {
            return 1;
        }
        if (rank >= 9) {
            return 10;
        }
        return rank + 1;
    }

    public String toString() {
        return this.cardFace;
    }
}
