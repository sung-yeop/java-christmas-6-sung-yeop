package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DrinkMenu {

    COKE("제로콜라", 3000),
    WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000),
    NONE("없음", 0);

    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    DrinkMenu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static int getPriceWithName(String name){
        return Arrays.stream(DrinkMenu.values()).filter(DrinkMenu -> DrinkMenu.name.equals(name)).findAny()
                .orElse(NONE).getPrice();
    }

}
