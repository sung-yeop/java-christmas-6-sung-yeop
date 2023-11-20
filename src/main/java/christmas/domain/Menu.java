package christmas.domain;

import java.util.Arrays;

public enum Menu {

    STAKE("티본스테이크", 55000, "main"),
    LEAF("바비큐립", 54000, "main"),
    PASTA("해산물파스타", 35000, "main"),
    CHRISMASPASTA("크리스마스파스타", 25000, "main"),

    COKE("제로콜라", 3000, "drink"),
    WINE("레드와인", 60000, "drink"),
    CHAMPAGNE("샴페인", 25000, "drink"),

    CAKE("초코케이크", 15000, "desert"),
    ICECREAM("아이스크림", 5000, "desert"),

    SOUP("양송이수프", 6000, "appetizer"),
    TAPAS("타파스", 5500, "appetizer"),
    SALAD("시저샐러드", 8000, "appetizer"),

    NONE("없음", 0, "none");

    private String name;
    private int price;
    private String type;

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    Menu(String name, int price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public static Menu getMenuWithName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getName().equals(menuName)).findAny().get();
    }

    public static int getPriceWithName(String name) {
        return Arrays.stream(Menu.values()).filter(Menu -> Menu.name.equals(name)).findAny()
                .orElse(NONE).getPrice();
    }

    // Drink만 포함된 메뉴가 아니면 True
    public static boolean isOnlyDrinkMenu(String name) {
        return Arrays.stream(Menu.values()).filter(n -> n.getName().equals(name))
                .allMatch(n -> n.getType().equals("drink"));
    }
}
