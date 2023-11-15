package christmas.model;

import christmas.domain.Menu;
import christmas.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {

    private static final int HOLIDAYDISCOUNT = 1000;
    private Discount discount;
    private Order order;
    private final static int weekend = 4;
    private final static int holiday = 2;

    @BeforeEach
    void setDiscount() {
        discount = new Discount();
        Map<Menu, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Menu.COKE, 2);
        orderMenu.put(Menu.WINE, 5);
        orderMenu.put(Menu.CHAMPAGNE, 7);
        orderMenu.put(Menu.LEAF, 3);
        order = new Order(holiday, orderMenu);
    }

    @DisplayName("평일과 주말에 따른 총 할인 금액 테스트")
    @Test
    void 평일_공휴일에_따른_할인_금액_테스트() {
        Map<String, Integer> discountResult = new HashMap<>();
        List<Menu> discountMenu = Arrays.stream(Menu.values()).filter(menu -> menu.getType().equals("main"))
                .collect(Collectors.toList());
        discount.weekEndOrHolidayTotalDiscount(order.getOrderDate(), discountResult, discountMenu, order.getOrderMenu());

        Assertions.assertThat(discountResult).containsEntry("주말 할인", 3 * 2023);


    }

    @DisplayName("혜택 증정 내역 확인 / 24일 공휴일")
    @Test
    void 총_할인_내역을_정리한_맵_생성_테스트() {
        Map<Menu, Integer> orderMenu = new HashMap<>();
        orderMenu.put(Menu.CAKE, 3);
        orderMenu.put(Menu.CHAMPAGNE, 5);
        Order order = new Order(24, orderMenu);

        Map<String, Integer> TotalDiscount = discount.discountTotal(
                order.getOrderDate(), order.getOrderMenu(), order.orderAmount());

        Assertions.assertThat(TotalDiscount).containsEntry("특별 할인", 1000);
        Assertions.assertThat(TotalDiscount).doesNotContainKey("주말 할인");
    }

    @DisplayName("12월 1일부터 하루가 지날때마다 1000원 + 100원씩 할인")
    @ParameterizedTest
    @ValueSource(ints = {24, 25, 10, 1})
    void 할인_테스트_크리스마스_디데이(int date) {
        Assertions.assertThat(discount.discountChristmas(date)).isEqualTo(1000 + (date - 1) * 100);
    }

    @DisplayName("12월 25일 이후부터는 크리스마스 디데이 할인 적용 X")
    @ParameterizedTest
    @ValueSource(ints = {30, 31, 26})
    void 할인_테스트_크리스마스_디데이_X(int date) {
        Assertions.assertThat(discount.discountChristmas(date)).isEqualTo(0);
    }

    @DisplayName("사용자가 할인 전 구매한 총 비용이 12만원 이상일 경우 true를 반환하여 샴페인 제공")
    @ParameterizedTest
    @ValueSource(ints = {120000, 20000000})
    void 샴페인_증정_테스트(int orderAmount) {
        Assertions.assertThat(discount.eventChampagne(orderAmount)).isEqualTo(true);
    }

    @DisplayName("사용자가 입력한 날짜가 공휴일이면 1000원 할인 / 외에는 0원 반환")
    @ParameterizedTest
    @ValueSource(ints = {3, 25, 24, 31})
    void 공휴일_할인_테스트(int date) {
        assertEquals(discount.discountStar(date), HOLIDAYDISCOUNT);
    }


    @DisplayName("사용자가 입력한 날짜에 따른 메뉴 할인 테스트")
    @Test
    void 할인_테스트() {
        List<Menu> mainMenuDiscount = discount.discountDay(30);
        List<Menu> desertMenuDiscount = discount.discountDay(31);


        Assertions.assertThat(mainMenuDiscount).contains(Menu.PASTA, Menu.CHRISMASPASTA, Menu.LEAF);

        Assertions.assertThat(desertMenuDiscount).contains(Menu.CAKE, Menu.ICECREAM);
    }

}