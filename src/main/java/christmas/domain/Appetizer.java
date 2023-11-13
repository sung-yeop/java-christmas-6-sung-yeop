package christmas.domain;

import java.util.Arrays;

public enum Appetizer implements Menu {

    SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    SALAD("시저샐러드", 8000),
    NONE("없음", 0);

    private String name;
    private int price;


    Appetizer(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static int getPriceWithName(String name) {
        return Arrays.stream(Appetizer.values()).filter(Appetizer -> Appetizer.name.equals(name)).findAny().
                orElse(NONE).getPrice();
    }

}
