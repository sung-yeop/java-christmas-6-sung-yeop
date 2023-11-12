package christmas.validate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidateTest {
    Validate validate;

    @BeforeEach
    void Before() {
        validate = new Validate();
    }

    @DisplayName("주문한 메뉴의 개수가 조건에 맞지 않는 경우")
    @ParameterizedTest
    @ValueSource(ints = {-1, -100})
    void 주문_개수_테스트(int orderNumber) {
        assertThatThrownBy(() -> validate.menuOrderNumberValidate(orderNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 주문한 메뉴가 존재하지 않는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"지로콜라", "케이크", "치킨", "피자"})
    void 메뉴_존재_X(String menuName) {
        assertThatThrownBy(() -> validate.menuValidate(menuName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴판에 주문한 메뉴가 존재하는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"제로콜라", "초코케이크", "티본스테이크", "타파스"})
    void 메뉴_존재_O(String menuName) {
        assertThatNoException().isThrownBy(() -> validate.menuValidate(menuName));
    }

    @DisplayName("1 ~ 31 외의 입력은 모두 에러 처리")
    @ParameterizedTest
    @ValueSource(ints = {100, -1, 150})
    void 날짜_유효성_검사_오류O(int visitDate) {

        //given
        assertThatThrownBy(() -> validate.visitDateValidate(visitDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1 ~ 31 사이의 입력은 모두 정상 기능")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 6, 31})
    void 날짜_유효성_검사_오류X(int visitDate) {
        assertThatNoException().isThrownBy(() -> validate.visitDateValidate(visitDate));
    }
}