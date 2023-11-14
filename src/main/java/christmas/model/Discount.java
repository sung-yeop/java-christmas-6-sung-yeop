package christmas.model;

import christmas.domain.Holiday;
import christmas.domain.Menu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Discount {

    private final static int DISCOUNTUNIT = 100;
    private final static int CHRISTMASDATE = 25;
    private final static int BASICDISCOUNT = 1000;
    private final static int PERIOD = 7;
    private final static int DISCOUNT = 2023;
    private final static int STARTDISCOUNT = 1000;
    private final static int NOTDISCOUNT = 0;
    private final static int CHAMPAGNEEVENT = 120000;

    public void discountTotal(int date, Map<String, Integer> orderMenu) {

    }

    public String eventChampagne(int orderAmount) {
        if (orderAmount >= CHAMPAGNEEVENT) {
            return Menu.CHAMPAGNE.getName();
        }
        return null;
    }

    public int discountStar(int date) {
        if (Arrays.stream(Holiday.values()).anyMatch(i -> i.getDate() == date)) {
            return STARTDISCOUNT;
        }
        return NOTDISCOUNT;
    }

    public int discountChristmas(int date) {
        int result = 0;
        if (date <= CHRISTMASDATE) {
            result = BASICDISCOUNT + DISCOUNTUNIT * (date - 1);
        }
        return result;
    }

    public Map<Menu, Integer> discountDay(int date) {
        Map<Menu, Integer> result = null;

        if (!discountWeekday(date).isEmpty()) {
            result = discountWeekday(date);
            return result;
        }

        if (!discountHoliday(date).isEmpty()) {
            result = discountHoliday(date);
            return result;
        }

        return result;
    }

    private Map<Menu, Integer> discountWeekday(int date) {
        Map<Menu, Integer> result = new HashMap<>();
        if (date % PERIOD != 1 && date % PERIOD != 2) {
            result = Arrays.stream(Menu.values()).filter(menu -> menu != menu.NONE)
                    .filter(menu -> !menu.getType().equals("desert"))
                    .collect(Collectors.toMap(menu -> Menu.getMenuWithName(menu.getName()), Menu::getPrice));
        }

        for (Menu menu : result.keySet()) {
            result.put(menu, result.get(menu) - DISCOUNT);
        }

        return result;
    }

    private Map<Menu, Integer> discountHoliday(int date) {
        Map<Menu, Integer> result = new HashMap<>();
        if (date % PERIOD == 1 || date % PERIOD == 2) {
            result = Arrays.stream(Menu.values()).filter(menu -> menu != menu.NONE)
                    .collect(Collectors.toMap(menu -> Menu.getMenuWithName(menu.getName()), Menu::getPrice));
        }

        for (Menu menu : result.keySet()) {
            result.put(menu, result.get(menu) - DISCOUNT);
        }

        return result;
    }

}
