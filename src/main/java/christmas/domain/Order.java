package christmas.domain;

import java.util.Map;

public class Order {

    private int orderDate;
    private Map<String, Integer> orderMenu;

    public Order(int orderDate, Map<String, Integer> orderMenu) {
        this.orderDate = orderDate;
        this.orderMenu = orderMenu;
    }

    public int getOrderDate() {
        return orderDate;
    }

    public Map<String, Integer> getOrderMenu() {
        return orderMenu;
    }

    public int orderAmount() {
        int amount = 0;
        for (String input : orderMenu.keySet()) {
            amount += menuPrice(input) * orderMenu.get(input);
        }
        return amount;
    }

    private int menuPrice(String key) {
        int amount = 0;
        amount += Appetizer.getPriceWithName(key);
        amount += DesertMenu.getPriceWithName(key);
        amount += DrinkMenu.getPriceWithName(key);
        amount += MainMenu.getPriceWithName(key);
        return amount;
    }


}
