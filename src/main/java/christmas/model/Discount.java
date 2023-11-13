package christmas.model;

import christmas.domain.DesertMenu;
import christmas.domain.Holiday;
import christmas.domain.MainMenu;

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

    public Map<String, Integer> discountDay(int date) {
        Map<String, Integer> result = null;

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

    private Map<String, Integer> discountWeekday(int date) {
        Map<String, Integer> result = new HashMap<>();
        if (date % PERIOD != 2 && date % PERIOD != 3) {
            result = Arrays.stream(DesertMenu.values()).filter(DesertMenu -> DesertMenu != DesertMenu.NONE)
                    .collect(Collectors.toMap(DesertMenu::getName, DesertMenu::getPrice));
        }

        for (String input : result.keySet()) {
            result.replace(input, result.get(input) - DISCOUNT);
        }

        return result;
    }

    private Map<String, Integer> discountHoliday(int date) {
        Map<String, Integer> result = new HashMap<>();
        if (date % PERIOD == 2 || date % PERIOD == 3) {
            result = Arrays.stream(MainMenu.values()).filter(MainMenu -> MainMenu != MainMenu.NONE)
                    .collect(Collectors.toMap(MainMenu::getName, MainMenu::getPrice));
        }

        for (String input : result.keySet()) {
            result.replace(input, result.get(input) - DISCOUNT);
        }

        return result;
    }

}
