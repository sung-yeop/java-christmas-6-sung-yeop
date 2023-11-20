package christmas.domain;

import java.util.Map;

public class Order {

    private int orderDate;
    private Map<Menu, Integer> orderMenu;

    public Order(int orderDate, Map<Menu, Integer> orderMenu) {
        this.orderDate = orderDate;
        this.orderMenu = orderMenu;
    }

    public int getOrderDate() {
        return orderDate;
    }

    public Map<Menu, Integer> getOrderMenu() {
        return orderMenu;
    }

    public int orderAmount() {
        int result = 0;
        for (Menu menu : orderMenu.keySet()) {
            result += Menu.getPriceWithName(menu.getName()) * orderMenu.get(menu);
        }
        return result;
    }
}
