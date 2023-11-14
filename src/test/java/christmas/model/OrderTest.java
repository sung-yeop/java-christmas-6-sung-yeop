package christmas.model;

import christmas.domain.DesertMenu;
import christmas.domain.DrinkMenu;
import christmas.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class OrderTest {

    private Order order;
    private static final int weekend = 1;
    private static final int holiday = 25;

    @BeforeEach
    void setOrder() {
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put("초코케이크", 3);
        orderMenu.put("샴페인", 5);
        order = new Order(weekend, orderMenu);
    }

    @Test
    void 할인_전_구입_금액_반환() {
        Assertions.assertThat(order.orderAmount())
                .isEqualTo(DesertMenu.CAKE.getPrice() * 3 + DrinkMenu.CHAMPAGNE.getPrice() * 5);
    }


}