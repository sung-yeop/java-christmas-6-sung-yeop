package christmas;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @DisplayName("샴페인 제공 시, 총 혜택 금액 - 샴페인 금액이 최종 할인 금액")
    @Test
    void 샴페인_제공_시_최종_할인_금액_확인() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<총혜택 금액>" + LINE_SEPARATOR + "-31,246원",
                    "<할인 후 예상 결제 금액>" + LINE_SEPARATOR + "135,754원"
            );
        });
    }

    @DisplayName("혜택 내역에는 제공되는 혜택만 출력")
    @Test
    void 혜택_내역에는_제공되는_혜택만_출력() {
        assertSimpleTest(() -> {
            run("25", "제로콜라-5,티본스테이크-2");
            assertThat(output()).doesNotContain(
                    "주말 할인", "평일 할인"
            );
        });
    }

    @DisplayName("주문 형식이 맞더라도 메뉴판에 존재하지 않는 메뉴를 주문하면 작동 X")
    @Test
    void 메뉴판에_존재하지_않는_메뉴_주문시_에러메세지_출력() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-10,치킨-5");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @DisplayName("음료수만 주문시, 주문 불가")
    @Test
    void 음료수만_주문하면_혜택_X() {
        assertSimpleTest(() -> {
            run("25", "제로콜라-5,레드와인-10");
            assertThat(output()).contains(
                    "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."
            );
        });
    }

    @DisplayName("25일(star) 주문 시, 특별할인 1000원이 적용되는지 테스트")
    @Test
    void 별표시_되어있는_날_주문시_특별할인_출력() {
        assertSimpleTest(() -> {
            run("25", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "특별 할인: -1,000원"
            );
        });
    }

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>",
                    "<증정 메뉴>",
                    "<혜택 내역>",
                    "<총혜택 금액>",
                    "<할인 후 예상 결제 금액>",
                    "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
