package christmas.validate;

import christmas.constants.Constants;
import christmas.domain.Appetizer;
import christmas.domain.DesertMenu;
import christmas.domain.DrinkMenu;
import christmas.domain.MainMenu;

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


    public static void integerTypeValidate(String number) {
        try {
            checkSum(!number.matches(integerPattern));
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage);
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
            System.out.println(errorMessage);
            throw new IllegalArgumentException();
        }
    }

    public static void menuDuplicationValidate(String order) {
        List<String> menuName = replaceStringToName(order);
        try {
            checkSum(menuName.stream().distinct().collect(Collectors.toList()).size() != menuName.size());
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage);
            throw new IllegalArgumentException();
        }
    }

    public static void menuOrderNumberValidate(String order) {
        List<Integer> orderNumbers = replaceStringToCount(order);
        int sum = 0;
        try {
            for (Integer orderNumber : orderNumbers) {
                sum += orderNumber;
                checkNumber(orderNumber);
            }
            checkSum(sum > MENUMAXCOUNT);

        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage);
            throw new IllegalArgumentException();
        }
    }

    private static void checkSum(boolean sum) {
        if (sum) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkNumber(Integer orderNumber) {
        checkSum(orderNumber < MIN);
    }

    public static void menuValidate(String order) {
        List<String> menuName = replaceStringToName(order);
        try {
            for (String input : menuName) {
                checkMenu(input);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage);
            throw new IllegalArgumentException();
        }
    }

    private static void checkMenu(String menuName) {
        checkSum(Appetizer.getPriceWithName(menuName) == 0 && DesertMenu.getPriceWithName(menuName) == 0
                && DrinkMenu.getPriceWithName(menuName) == 0 && MainMenu.getPriceWithName(menuName) == 0);
    }

    public static void visitDateValidate(int visitDate) {
        try {
            checkVisitDate(visitDate);
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage);
            throw new IllegalArgumentException();
        }
    }

    private static void checkVisitDate(int visitDate) {
        checkSum(visitDate > Constants.MAXDATE || visitDate < Constants.MINDATE);
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
