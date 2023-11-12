package christmas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DesertMenu {
    CAKE("초코케이크", 15000),
    ICECREAM("아이스크림", 5000),
    NONE("없음", 0);

    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    DesertMenu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static int getPriceWithName(String name){
        return Arrays.stream(DesertMenu.values()).filter(DesertMenu -> DesertMenu.name.equals(name)).findAny()
                .orElse(NONE).getPrice();
    }
}
