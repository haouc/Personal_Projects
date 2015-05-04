/**
 * Created by Hao on 11/16/2014.
 */
public class Bet {
    private int myBet;
    private final int initialBet = 1000;

    private int balance;

    public Bet(int money){
        this.myBet = money;
    }

    public int getBet(){
        return myBet;
    }

    public int betIn(int in){
        return in;
    }

    public int setBalance(int myBalance){
        return myBalance;
    }

    public int getBalance(String result, int thisBet){
        if(result.equals("win")) balance = balance + thisBet;
        else if (result.equals("lose")) balance = balance - thisBet;
        return balance;
    }

}
