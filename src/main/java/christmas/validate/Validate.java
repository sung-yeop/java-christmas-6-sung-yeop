package christmas.validate;

import christmas.constants.Constants;
import christmas.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class Validate {
    public static final String errorMessage = "[ERROR]";
    public static final int MIN = 1;

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
