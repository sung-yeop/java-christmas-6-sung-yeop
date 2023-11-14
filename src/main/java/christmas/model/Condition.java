package christmas.model;

import christmas.domain.Menu;

import java.util.Map;

public class Condition {

    private final static int CONDITION = 10000;
    private final static int ORDERCOUNTCONDITION = 20;

    public boolean checkEventConditionAmount(int orderAmount) {
        if (orderAmount >= CONDITION) {
            return true;
        }
        return false;
    }

    public boolean checkEventConditionMenu(Map<Menu, Integer> orderMenu) {
        if (orderMenu.keySet().stream().allMatch(menu -> Menu.isOnlyDrinkMenu(menu.getName()))) {
            return false;
        }
        return true;
    }

    public boolean checkEventConditionMenuCount(Map<Menu, Integer> orderMenu) {
        if (orderMenu.values().stream().mapToInt(Integer::intValue).sum() > ORDERCOUNTCONDITION) {
            return false;
        }
        return true;
    }
}
