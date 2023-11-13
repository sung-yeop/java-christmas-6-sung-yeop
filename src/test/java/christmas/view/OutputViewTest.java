package christmas.view;

import christmas.domain.DrinkMenu;
import christmas.domain.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class OutputViewTest {

    private OutputView outputView;

    @BeforeEach
    void setOutputView() {
        outputView = new OutputView();
    }

    @Test
    void 메뉴_출력_테스트() {
        Map<String, Integer> orderMenu = new HashMap<>();
        orderMenu.put(MainMenu.LEAF.getName(), 3);
        orderMenu.put(DrinkMenu.CHAMPAGNE.getName(), 4);

        outputView.viewMenuOutput(orderMenu);
    }

}