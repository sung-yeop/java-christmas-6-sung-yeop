package christmas.model;

import camp.nextstep.edu.missionutils.Console;
import christmas.validate.Validate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<String, Integer> inputOrderMenu() {
        String input = Console.readLine();
        try {
            Validate.menuFormatValidate(input);
            Validate.menuDuplicationValidate(input);
            Validate.menuValidate(input);
            Validate.menuOrderNumberValidate(input);
        } catch (IllegalArgumentException e) {
            inputOrderMenu();
        }
        return createOrder(input);
    }

    private Map<String, Integer> createOrder(String input) {
        Map<String, Integer> result = new HashMap<>();
        List<String[]> pair = Arrays.stream(input.split(delimeterComma))
                .map(s -> s.split(delimeterDash)).collect(Collectors.toList());
        for (String[] strings : pair) {
            result.put(strings[0], Integer.parseInt(strings[1]));
        }
        return result;
    }
}
