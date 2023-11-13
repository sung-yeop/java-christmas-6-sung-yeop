package christmas.model;

import christmas.domain.DrinkMenu;
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

    @DisplayName("사용자가 할인 전 구매한 총 비용이 12만원 이상일 경우 샴페인 스트링을 반환")
    @ParameterizedTest
    @ValueSource(ints = {120000, 20000000})
    void 샴페인_증정_테스트(int orderAmount) {
        Assertions.assertThat(discount.eventChampagne(orderAmount)).isEqualTo(DrinkMenu.CHAMPAGNE.getName());
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
        Map<String, Integer> mainMenuDiscount = discount.discountDay(30);
        Map<String, Integer> desertMenuDiscount = discount.discountDay(29);


        Assertions.assertThat(mainMenuDiscount).containsEntry("티본스테이크", 52977);
        Assertions.assertThat(mainMenuDiscount).containsEntry("해산물파스타", 32977);
        Assertions.assertThat(mainMenuDiscount).containsEntry("크리스마스파스타", 22977);
        Assertions.assertThat(mainMenuDiscount).containsEntry("바비큐립", 51977);

        Assertions.assertThat(desertMenuDiscount).containsEntry("초코케이크", 15000 - 2023);
        Assertions.assertThat(desertMenuDiscount).containsEntry("아이스크림", 5000 - 2023);
    }

}