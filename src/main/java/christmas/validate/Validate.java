package christmas.validate;

import christmas.constants.Constants;
import christmas.domain.Appetizer;
import christmas.domain.DesertMenu;
import christmas.domain.DrinkMenu;
import christmas.domain.MainMenu;

public class Validate {
    public static final String errorMessage = "[ERROR]";

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
