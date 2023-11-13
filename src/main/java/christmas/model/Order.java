package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private int orderAmount;
    private int orderDate;
    private Map<String, Integer> orderMenu = new HashMap<>();

    public Order(int orderAmount, int orderDate, Map<String, Integer> orderMenu) {
        this.orderAmount = orderAmount;
        this.orderDate = orderDate;
        this.orderMenu = orderMenu;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public int getOrderDate() {
        return orderDate;
    }

    public Map<String, Integer> getOrderMenu() {
        return orderMenu;
    }
}
