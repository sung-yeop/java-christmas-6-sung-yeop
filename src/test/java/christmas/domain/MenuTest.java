package christmas.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {

    private static final String AppetizerNameTest = "타파스";
    private static final String DesertMenuNameTest = "초코케이크";
    private static final String DrinkMenuNameTest = "제로콜라";
    private static final String MainMenuNameTest = "티본스테이크";

    @Test
    void 이름으로_가격_가져오기_테스트() {
        assertThat(Menu.getPriceWithName(AppetizerNameTest)).isEqualTo(Menu.TAPAS.getPrice());
        assertThat(Menu.getPriceWithName(DesertMenuNameTest)).isEqualTo(Menu.CAKE.getPrice());
        assertThat(Menu.getPriceWithName(DrinkMenuNameTest)).isEqualTo(Menu.COKE.getPrice());
        assertThat(Menu.getPriceWithName(MainMenuNameTest)).isEqualTo(Menu.STAKE.getPrice());
    }


}