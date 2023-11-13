package christmas.validate;

import christmas.constants.Constants;
import christmas.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Validate {
    public static final String errorMessage = "[ERROR]";
    public static final int MIN = 1;
    public static final String menuInputPattern = "(([가-힣]*)-[0-9]*)";
    private static final String integerPattern = "([0-9]*)";

    public static void integerTypeValidate(String number) {
        try {
            if (!number.matches(integerPattern)) {
                throw new IllegalArgumentException();
            }
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

    public static void menuDuplicationValidate(List<Menu> menuName) {
        try {
            if (menuName.stream().distinct().collect(Collectors.toList()).size() != menuName.size()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage);
            throw new IllegalArgumentException();
        }
    }

    public static void menuOrderNumberValidate(int orderNumber) {
        try {
            if (orderNumber < MIN) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage);
            throw new IllegalArgumentException();
        }
    }

    public static void menuValidate(String menuName) {
        try {
            checkMenu(menuName);
            ;
        } catch (IllegalArgumentException e) {
            System.out.println(errorMessage);
            throw new IllegalArgumentException();
        }
    }

    private static void checkMenu(String menuName) {
        if (Appetizer.getPriceWithName(menuName) == 0 && DesertMenu.getPriceWithName(menuName) == 0
                && DrinkMenu.getPriceWithName(menuName) == 0 && MainMenu.getPriceWithName(menuName) == 0) {
            throw new IllegalArgumentException();
        }
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
        if (visitDate > Constants.MAXDATE || visitDate < Constants.MINDATE) {
            throw new IllegalArgumentException();
        }
    }

}
