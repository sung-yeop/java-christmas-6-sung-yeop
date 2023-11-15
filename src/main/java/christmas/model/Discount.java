package christmas.model;

import christmas.domain.Badge;
import christmas.domain.Holiday;
import christmas.domain.Menu;

import java.util.*;
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

    public Badge discountBadge(int discountAmount) {
        return Arrays.stream(Badge.values()).sorted(Comparator.reverseOrder())
                .filter(badge -> badge.getCutLine() < discountAmount).findFirst().get();
    }

    public Map<String, Integer> discountTotal(int date, Map<Menu, Integer> orderMenu, int payAmount) {
        Map<String, Integer> discountResult = new LinkedHashMap<>();

        discountResult.put(christmasDayDiscount, discountChristmas(date));
        weekEndOrHolidayTotalDiscount(date, discountResult, discountDay(date), orderMenu);

        if (discountStar(date) != 0) {
            discountResult.put(specialDiscount, discountStar(date));
        }
        if (eventChampagne(payAmount)) {
            discountResult.put(bonusEvent, Menu.CHAMPAGNE.getPrice());
        }
        return discountResult;
    }

    public int discountTotalAmount(Map<String, Integer> discountTotal) {
        return discountTotal.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int discountTotalAmountExceptBonus(Map<String, Integer> discountTotal) {
        return discountTotal.keySet().stream().filter(key -> !key.equals(bonusEvent))
                .mapToInt(key -> discountTotal.get(key)).sum();
    }

    public void weekEndOrHolidayTotalDiscount(int date, Map<String, Integer> discountResult,
                                              List<Menu> discount, Map<Menu, Integer> orderMenu) {
        int sum = orderMenu.keySet().stream()
                .filter(menu -> discount.contains(menu)).mapToInt(key -> orderMenu.get(key) * DISCOUNT).sum();
        if (weekOrHoliday(date)) {
            discountResult.put(holidayDiscount, sum);
            return;
        }
        discountResult.put(weekendDiscount, sum);
    }

    public boolean eventChampagne(int orderAmount) {
        if (orderAmount >= CHAMPAGNEEVENT) {
            return true;
        }
        return false;
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

    public List<Menu> discountDay(int date) {
        if (weekOrHoliday(date)) {
            return discountHoliday();
        }
        return discountWeekday();
    }

    //true : 주말 | false : 평일
    private boolean weekOrHoliday(int date) {
        if (date % 7 == 1 || date % 7 == 2) {
            return true;
        }
        return false;
    }

    private List<Menu> discountWeekday() {
        return Arrays.stream(Menu.values()).filter(menu -> menu != menu.NONE)
                .filter(menu -> menu.getType().equals("desert"))
                .collect(Collectors.toList());
    }

    private List<Menu> discountHoliday() {
        return Arrays.stream(Menu.values()).filter(menu -> menu != menu.NONE)
                .filter(menu -> menu.getType().equals("main"))
                .collect(Collectors.toList());
    }
}
