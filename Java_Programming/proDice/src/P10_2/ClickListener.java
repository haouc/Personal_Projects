package P10_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hao on 10/29/2014.
 */
public class ClickListener implements ActionListener{
    private int n = 0;
    public void actionPerformed(ActionEvent event){
        n++;
        System.out.println("I was clicked " + n + " times!");
    }
}
