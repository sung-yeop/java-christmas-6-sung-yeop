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
    private final static String christmasDayDiscount = "크리스마스 디데이 할인";
    private final static String weekendDiscount = "평일 할인";
    private final static String holidayDiscount = "주말 할인";
    private final static String specialDiscount = "특별 할인";
    private final static String bonusEvent = "증정 이벤트";

    public Map<String, Integer> discountTotal(int date, Map<Menu, Integer> orderMenu, int payAmount) {
        Map<String, Integer> discount = new HashMap<>();
        int discountDayAmount = getDiscountDayAmount(date, orderMenu);

        discount.put(christmasDayDiscount, discountChristmas(date));
        weekEndOrHolidayDiscount(date, discount, discountDayAmount);

        if (discountStar(date) != 0) {
            discount.put(specialDiscount, discountStar(date));
        }
        if (eventChampagne(payAmount) != null) {
            discount.put(bonusEvent, Menu.CHAMPAGNE.getPrice());
        }
        return discount;
    }

    private int getDiscountDayAmount(int date, Map<Menu, Integer> orderMenu) {
        int discountDayAmount = (int) discountDay(date).keySet().stream()
                .filter(orderMenu.keySet()::equals).count() * DISCOUNT;
        return discountDayAmount;
    }

    private void weekEndOrHolidayDiscount(int date, Map<String, Integer> discount, int discountDayAmount) {
        if (weekOrHoliday(date)) {
            if (discountDay(date).keySet().stream().anyMatch(key -> key.getType().equals("desert"))) {
                discount.put(weekendDiscount, discountDayAmount);
            }
            discount.put(weekendDiscount, 0);
            return;
        }
        if (discountDay(date).keySet().stream().anyMatch(key -> key.getType().equals("main"))) {
            discount.put(holidayDiscount, discountDayAmount);
        }
        discount.put(holidayDiscount, 0);
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
        Map<Menu, Integer> result = new HashMap<>();

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

    //true : 평일 | false : 주말
    private boolean weekOrHoliday(int date) {
        if (date % PERIOD != 1 && date % PERIOD != 2) {
            return true;
        }
        return false;
    }

    private Map<Menu, Integer> discountWeekday(int date) {
        Map<Menu, Integer> result = new HashMap<>();
        if (weekOrHoliday(date)) {
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
        if (!weekOrHoliday(date)) {
            result = Arrays.stream(Menu.values()).filter(menu -> menu != menu.NONE)
                    .collect(Collectors.toMap(menu -> Menu.getMenuWithName(menu.getName()), Menu::getPrice));
        }
        for (Menu menu : result.keySet()) {
            result.put(menu, result.get(menu) - DISCOUNT);
        }
        return result;
    }
}
