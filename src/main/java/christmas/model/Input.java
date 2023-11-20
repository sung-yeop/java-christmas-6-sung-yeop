package christmas.model;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Menu;
import christmas.validate.Validate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Input {

    private static final String delimeterComma = "(,)";
    private static final String delimeterDash = "(-)";

    public int inputOrderDate() {
        String input = Console.readLine();
        try {
            Validate.integerTypeValidate(input);
            int date = Integer.parseInt(input);
            Validate.visitDateValidate(date);
        } catch (IllegalArgumentException e) {
            inputOrderDate();
        }
        return Integer.parseInt(input);
    }

    public Map<Menu, Integer> inputOrderMenu() {
        String input = Console.readLine();
        try {
            Validate.menuFormatValidate(input);
            Validate.isOnlyDrinkValidate(input);
            Validate.menuDuplicationValidate(input);
            Validate.menuValidate(input);
            Validate.menuOrderNumberValidate(input);
        } catch (IllegalArgumentException e) {
            inputOrderMenu();
        }
        return createOrder(input);
    }

    private Map<Menu, Integer> createOrder(String input) {
        Map<Menu, Integer> result = new LinkedHashMap<>();
        List<String[]> pair = Arrays.stream(input.trim().split(delimeterComma))
                .map(s -> s.split(delimeterDash)).toList();
        for (String[] strings : pair) {
            result.put(Menu.getMenuWithName(strings[0]), Integer.parseInt(strings[1]));
        }
        return result;
    }
}
