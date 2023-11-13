package christmas.model;

import christmas.domain.DrinkMenu;

import java.util.Map;

public class EventCondition {

    private final static int CONDITION = 10000;

    public boolean checkEventConditionAmount(int orderAmount) {
        if (orderAmount > CONDITION) {
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
}
