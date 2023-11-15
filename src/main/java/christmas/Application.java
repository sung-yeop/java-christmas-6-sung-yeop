package christmas;

import christmas.controller.Controller;
import christmas.model.BadgeEvent;
import christmas.model.Condition;
import christmas.model.Discount;
import christmas.model.Input;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Controller controller = new Controller(new BadgeEvent(), new Condition(), new Discount()
                , new Input(), new OutputView(), new InputView());
        controller.start();
    }
}
