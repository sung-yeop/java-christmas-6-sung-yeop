package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class EventConditionTest {

    private EventCondition eventCondition;

    @BeforeEach
    void setEventCondition() {
        eventCondition = new EventCondition();
    }

    @DisplayName("이벤트 발생 조건 테스트 / 음료수만 주문")
    @Test
    void 음료수만_주문하면_이벤트_발생_X() {
        //given
        Map<String, Integer> order = new HashMap<>();
        order.put("제로콜라", 100);
        order.put("레드와인", 100);
        order.put("샴페인", 1000);

        Assertions.assertThat(eventCondition.checkEventConditionMenu(order)).isEqualTo(false);
    }

    @DisplayName("이벤트 발생 조건 테스트 / 음료수만 주문")
    @Test
    void 음료수만_주문하지_않은_경우_이벤트_발생_O() {
        //given
        Map<String, Integer> order = new HashMap<>();
        order.put("스테이크", 100);
        order.put("레드와인", 100);
        order.put("샴페인", 1000);

        Assertions.assertThat(eventCondition.checkEventConditionMenu(order)).isEqualTo(true);
    }


}