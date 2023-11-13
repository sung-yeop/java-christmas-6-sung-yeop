package christmas.model;

import christmas.domain.DrinkMenu;

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

    public boolean checkEventConditionMenu(Map<String, Integer> orderMenu) {
        if (orderMenu.keySet().stream().filter(DrinkMenu::checkMenu).allMatch(b -> b.equals(true))) {
            return false;
        }
        return true;
    }

    public boolean checkEventConditionMenuCount(Map<String, Integer> orderMenu) {
        if (orderMenu.values().stream().mapToInt(Integer::intValue).sum() > ORDERCOUNTCONDITION) {
            return false;
        }
        return true;
    }
}
