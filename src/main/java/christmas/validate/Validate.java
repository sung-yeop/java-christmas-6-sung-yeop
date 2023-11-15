package christmas.validate;

import christmas.constants.Constants;
import christmas.domain.Menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Validate {
    private static final String errorMessage = "[ERROR]";
    private static final int MIN = 1;
    private static final int MENUMAXCOUNT = 20;
    private static final String menuInputPattern = "(([가-힣]*)-[0-9]*)";
    private static final String integerPattern = "([0-9]*)";
    private static final String delimeterPattern = "(-[0-9]*.)";
    private static final String delimeterNamePattern = "(.[가-힣]+-)";
    private static final String mentionErrorDate = " 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String mentionErrorMenu = " 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    public static void isOnlyDrinkValidate(String order) {
        List<String> menuName = replaceStringToName(order);
        try {
            check(!menuName.stream().allMatch(menu -> Menu.isOnlyDrinkMenu(menu)));
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage + mentionErrorMenu);
        }
    }

    public static void integerTypeValidate(String number) {
        try {
            check(number.matches(integerPattern));
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage + mentionErrorDate);
            throw new IllegalArgumentException();
        }
    }

    public static void menuFormatValidate(String order) {
        List<String> orderList = Arrays.stream(order.split(",")).toList();
        try {
            if (!orderList.stream().allMatch(s -> s.matches(menuInputPattern))) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage + mentionErrorMenu);
            throw new IllegalArgumentException();
        }
    }

    public static void menuDuplicationValidate(String order) {
        List<String> menuName = replaceStringToName(order);
        try {
            check(menuName.stream().distinct().collect(Collectors.toList()).size() == menuName.size());
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage + mentionErrorMenu);
            throw new IllegalArgumentException();
        }
    }

    public static void menuOrderNumberValidate(String order) {
        try {
            check(replaceStringToCount(order).stream().mapToInt(Integer::intValue).sum() <= MENUMAXCOUNT);
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage + mentionErrorMenu);
            throw new IllegalArgumentException();
        }
    }

    private static void check(boolean validate) {
        if (!validate) {
            throw new IllegalArgumentException();
        }
    }

    public static void menuValidate(String order) {
        List<String> menuName = replaceStringToName(order);
        try {
            for (String input : menuName) {
                check(Arrays.stream(Menu.values()).anyMatch(Menu -> Menu.getName().equals(input)));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage + mentionErrorMenu);
            throw new IllegalArgumentException();
        }
    }

    public static void visitDateValidate(int visitDate) {
        try {
            check(visitDate <= Constants.MAXDATE && visitDate >= Constants.MINDATE);
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage + mentionErrorDate);
            throw new IllegalArgumentException();
        }
    }

    private static List<String> replaceStringToName(String order) {
        return Arrays.stream(order.replaceAll(delimeterPattern, ",").split(","))
                .collect(Collectors.toList());
    }

    public static List<Integer> replaceStringToCount(String order) {
        return Arrays.stream(order.replaceAll(delimeterNamePattern, ",").split(","))
                .filter(s -> !s.equals(""))
                .map(Integer::parseInt).collect(Collectors.toList());
    }

}
