package christmas.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private static final String AppetizerNameTest = "타파스";
    private static final String DesertMenuNameTest = "초코케이크";
    private static final String DrinkMenuNameTest = "제로콜라";
    private static final String MainMenuNameTest = "티본스테이크";

    @Test
    void 이름으로_가격_가져오기_테스트(){
        assertThat(Appetizer.getPriceWithName(AppetizerNameTest)).isEqualTo(Appetizer.TAPAS.getPrice());
        assertThat(DesertMenu.getPriceWithName(DesertMenuNameTest)).isEqualTo(DesertMenu.CAKE.getPrice());
        assertThat(DrinkMenu.getPriceWithName(DrinkMenuNameTest)).isEqualTo(DrinkMenu.COKE.getPrice());
        assertThat(MainMenu.getPriceWithName(MainMenuNameTest)).isEqualTo(MainMenu.STAKE.getPrice());
    }


}