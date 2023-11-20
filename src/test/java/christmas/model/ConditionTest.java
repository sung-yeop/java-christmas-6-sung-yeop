package christmas.model;

import christmas.domain.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

class ConditionTest {

    private Condition condition;

    @BeforeEach
    void setEventCondition() {
        condition = new Condition();
    }

    @DisplayName("10000원 이상 주문하면 이벤트 발생")
    @ParameterizedTest
    @ValueSource(ints = {10000, 20000, 100000000})
    void 총_주문_금액이_10000원_이상이면_이벤트_발생_O(int orderAmount) {
        Assertions.assertThat(condition.checkEventConditionAmount(orderAmount)).isEqualTo(true);
    }

    @DisplayName("10000원 미만 주문시 이벤트 발생 X")
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 10})
    void 총_주문_금액이_10000원_미만이면_이벤트_발생_X(int orderAmount) {
        Assertions.assertThat(condition.checkEventConditionAmount(orderAmount)).isEqualTo(false);
    }


    @DisplayName("20개 이하 주문시 true 반환")
    @Test
    void 메뉴를_20개_이하로_주문하면_이벤트_발생_O() {
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.COKE, 2);
        order.put(Menu.WINE, 3);
        order.put(Menu.CHAMPAGNE, 10);

        Assertions.assertThat(condition.checkEventConditionMenuCount(order)).isEqualTo(true);
    }

    @DisplayName("20개 초과 주문시 false 반환")
    @Test
    void 메뉴를_20개_초과하여_주문하면_이벤트_발생_X() {
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.COKE, 100);
        order.put(Menu.WINE, 100);
        order.put(Menu.CHAMPAGNE, 1000);

        Assertions.assertThat(condition.checkEventConditionMenuCount(order)).isEqualTo(false);
    }

    @DisplayName("이벤트 발생 조건 테스트 / 음료수만 주문")
    @Test
    void 음료수만_주문하면_이벤트_발생_X() {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.COKE, 100);
        order.put(Menu.WINE, 100);
        order.put(Menu.CHAMPAGNE, 1000);

        Assertions.assertThat(condition.checkEventConditionMenu(order)).isEqualTo(false);
    }

    @DisplayName("이벤트 발생 조건 테스트 / 음료수만 주문")
    @Test
    void 음료수만_주문하지_않은_경우_이벤트_발생_O() {
        //given
        Map<Menu, Integer> order = new HashMap<>();
        order.put(Menu.COKE, 100);
        order.put(Menu.WINE, 100);
        order.put(Menu.CHAMPAGNE, 1000);

        Assertions.assertThat(condition.checkEventConditionMenu(order)).isEqualTo(false);
    }


}