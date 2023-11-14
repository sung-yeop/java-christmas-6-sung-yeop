package christmas.model;

import christmas.domain.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountTest {

    private static final int HOLIDAYDISCOUNT = 1000;

    private Discount discount;

    @BeforeEach
    void setDiscount() {
        discount = new Discount();
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

    @DisplayName("사용자가 할인 전 구매한 총 비용이 12만원 이상일 경우 샴페인 스트링을 반환")
    @ParameterizedTest
    @ValueSource(ints = {120000, 20000000})
    void 샴페인_증정_테스트(int orderAmount) {
        Assertions.assertThat(discount.eventChampagne(orderAmount)).isEqualTo(Menu.CHAMPAGNE.getName());
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
        Map<Menu, Integer> mainMenuDiscount = discount.discountDay(30);
        Map<Menu, Integer> desertMenuDiscount = discount.discountDay(29);


        Assertions.assertThat(mainMenuDiscount).containsEntry(Menu.STAKE, 52977);
        Assertions.assertThat(mainMenuDiscount).containsEntry(Menu.PASTA, 32977);
        Assertions.assertThat(mainMenuDiscount).containsEntry(Menu.CHRISMASPASTA, 22977);
        Assertions.assertThat(mainMenuDiscount).containsEntry(Menu.LEAF, 51977);

        Assertions.assertThat(desertMenuDiscount).containsEntry(Menu.CAKE, 15000 - 2023);
        Assertions.assertThat(desertMenuDiscount).containsEntry(Menu.ICECREAM, 5000 - 2023);
    }

}