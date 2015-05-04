package P10_35;

import java.util.ArrayList;

/**
 * Created by Hao on 10/31/2014.
 */
public class Menu {
    private String item;
    private double price;

    public Menu(String order, double price) {
        this.item = order;
        this.price = price;
    }

    public String getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }

}
