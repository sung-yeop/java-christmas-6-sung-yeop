package christmas.model;

import christmas.domain.Menu;
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
        Map<Menu, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Menu.CAKE, 3);
        orderMenu.put(Menu.CHAMPAGNE, 5);
        order = new Order(weekend, orderMenu);
    }

    @Test
    void 할인_전_구입_금액_반환() {
        Assertions.assertThat(order.orderAmount())
                .isEqualTo(45000 + 125000);
    }
}