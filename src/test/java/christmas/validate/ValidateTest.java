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

    @DisplayName("입력한 값이 정수형이면 에러 발생 X")
    @ParameterizedTest
    @ValueSource(strings = {"1", "200000", "123123123", "0"})
    void 입력_값이_정수형이면_에러_발생_X(String number) {
        assertThatNoException().isThrownBy(() -> validate.integerTypeValidate(number));
    }

    @DisplayName("입력한 값이 정수형이 아니면 에러 발생 O")
    @ParameterizedTest
    @ValueSource(strings = {"123liuhoiu", "asdf", "!@#!@#asdfq123123"})
    void 입력_값이_정수형이아니면_에러_발생_O(String notNumber) {
        assertThatThrownBy(() -> validate.integerTypeValidate(notNumber)).isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("주문한 메뉴 형식이 올바르지 않은 경우")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크-1,초코무스-2,피자-3,치킨-5,치킨0912"})
    void 메뉴_포맷_X(String orderMenu) {
        assertThatThrownBy(() -> validate.menuFormatValidate(orderMenu)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴 형식이 올바른 경우")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크-1,초코무스-2,피자-3,치킨-5"})
    void 메뉴_포맷_O(String orderMenu) {
        assertThatNoException().isThrownBy(() -> validate.menuFormatValidate(orderMenu));
    }

    @DisplayName("주문한 메뉴에 중복된 내용이 존재하는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크-1,초코무스-2,피자-3,치킨-5,피자-5"})
    void 메뉴_중복_O(String order) {
        assertThatThrownBy(() -> validate.menuDuplicationValidate(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문한 메뉴에 중복된 내용이 존재하지 않는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"초코케이크-1,초코무스-2,피자-3,치킨-5"})
    void 메뉴_중복_X(String order) {
        assertThatNoException().isThrownBy(() -> validate.menuDuplicationValidate(order));
    }


    @DisplayName("주문한 메뉴의 개수가 조건에 맞지 않는 경우")
    @ParameterizedTest
    @ValueSource(strings = {("초코케이크-1,초코무스-2,피자-3,치킨-5,피자-11"), ("초코케이크-1,초코무스-10,피자-15")})
    void 주문_개수_테스트(String order) {
        assertThatThrownBy(() -> validate.menuOrderNumberValidate(order))
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
    @ValueSource(strings = {"제로콜라-1,타파스-3,아이스크림-4"})
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