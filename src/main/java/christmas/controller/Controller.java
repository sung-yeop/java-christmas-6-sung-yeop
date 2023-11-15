package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.model.BadgeEvent;
import christmas.model.Condition;
import christmas.model.Discount;
import christmas.model.Input;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.LinkedHashMap;
import java.util.Map;

public class Controller {

    private BadgeEvent badgeEvent;
    private Condition condition;
    private Discount discount;
    private Input input;
    private OutputView outputView;
    private InputView inputView;
    private Order order;

    public Controller(BadgeEvent badgeEvent, Condition condition, Discount discount, Input input
            , OutputView outputView, InputView inputView) {
        this.badgeEvent = badgeEvent;
        this.condition = condition;
        this.discount = discount;
        this.input = input;
        this.outputView = outputView;
        this.inputView = inputView;
    }

    public void start() {
        setOrder();

        outputView.viewPreviewMessage();
        Map<String, Integer> discountTotal = new LinkedHashMap<>();

        if (checkCondition()) {
            discountTotal = discount.discountTotal(order.getOrderDate(),
                    order.getOrderMenu(), order.orderAmount());
            outputEvent(true, discountTotal);
        }

        if (!checkCondition()) {
            outputEvent(false, discountTotal);
        }
    }

    private void outputEvent(boolean benefit, Map<String, Integer> discountTotal) {
        outputView.viewMenuOutput(order.getOrderMenu());
        outputView.viewOrderAmountOutput(order.orderAmount());
        outputView.viewBonusMenuOutput(discount.eventChampagne(order.orderAmount()));
        outputView.viewBenefitOutput(discountTotal);
        outputView.viewAllBenefitAmount(benefit, discount.discountTotalAmount(discountTotal));
        outputView.viewAmountOfPayment(order.orderAmount() - discount.discountTotalAmountExceptBonus(discountTotal));
        outputView.viewBadge(badgeEvent.giveBadge(order.orderAmount()));
    }

    private boolean checkCondition() {
        return condition.checkEventConditionAmount(order.orderAmount()) && condition.checkEventConditionMenu(order.getOrderMenu())
                && condition.checkEventConditionMenuCount(order.getOrderMenu());
    }

    private void setOrder() {
        inputView.viewMainMessage();
        inputView.viewInputDateMessage();
        int date = input.inputOrderDate();
        inputView.viewInputMenuMessage();
        Map<Menu, Integer> orderMenu = input.inputOrderMenu();
        order = new Order(date, orderMenu);
    }


}
