package christmas.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

class DiscountTest {

    @DisplayName("사용자가 입력한 날짜에 따른 메뉴 할인 테스트")
    @Test
    void 할인_테스트() {
        Discount discount = new Discount();
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