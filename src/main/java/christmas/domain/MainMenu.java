package christmas.domain;

import java.util.Arrays;

public enum MainMenu implements Menu {

    STAKE("티본스테이크", 55000),
    LEAF("바비큐립", 54000),
    PASTA("해산물파스타", 35000),
    CHRISMASPASTA("크리스마스파스타", 25000),
    NONE("없음", 0);

    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    MainMenu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static int getPriceWithName(String name) {
        return Arrays.stream(MainMenu.values()).filter(MainMenu -> MainMenu.name.equals(name)).findAny()
                .orElse(NONE).getPrice();
    }
}
