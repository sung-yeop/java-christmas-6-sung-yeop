package christmas.validate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {
    Validate validate;

    private final static String errorMessage = "[ERROR]";

    @BeforeEach
    void Before() {
        validate = new Validate();
    }

    @DisplayName("1 ~ 31 외의 입력은 모두 에러 처리")
    @ParameterizedTest
    @ValueSource(ints = {100, -1, 150})
    void 날짜_유효성_검사_오류O(int visitDate) {

        //given
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> validate.visitDateValidate(visitDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1 ~ 31 사이의 입력은 모두 정상 기능")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 6, 31})
    void 날짜_유효성_검사_오류X(int visitDate){
        org.assertj.core.api.Assertions.assertThatNoException().isThrownBy(() -> validate.visitDateValidate(visitDate));
    }
}