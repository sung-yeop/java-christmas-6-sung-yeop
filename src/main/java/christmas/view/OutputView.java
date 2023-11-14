package christmas.view;

import christmas.domain.DrinkMenu;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private final static String menuOutput = "<주문 메뉴>";
    private final static String menuOutputFormat = "%s %d개\n";
    private final static String amountOutput = "<할인 전 총주문 금액>";
    private final static String amountOutputFormat = "%s원\n";
    private final static String bonusMenuOutput = "<증정 메뉴>";
    private final static String bonusMenuOutputFormatTrue = "%s %d개\n";
    private final static String OutputFormatNone = "%s\n";
    private final static String benefitOutput = "<혜택 내역>";
    private final static String benefitOutputFormatTrue = "크리스마스 디데이 할인: -%s원\n" + "평일 할인: -%s원\n" +
            "특별 할인: -%s원\n" + "증정 이벤트: -%s원\n";
    private final static String allBenefitAmount = "<총혜택 금액>";
    private final static String amountOfPayment = "<할인 후 예상 결제 금액>";
    private final static String amountFormat = "-%s원\n";
    private final static String badge = "<12월 이벤트 배지>";

    private final static int bonusMenuCount = 1;

    public void viewMenuOutput(Map<String, Integer> orderMenu) {
        System.out.println(menuOutput);
        String menuOutput = orderMenu.keySet().stream().map(k -> String.format(menuOutputFormat, k, orderMenu.get(k)))
                .collect(Collectors.joining());
        System.out.println(menuOutput);
    }

    public void viewOrderAmountOutput(String amount) {
        System.out.println(amountOutput);
        System.out.println(String.format(amountOutputFormat, amount));
    }

    public void viewBonusMenuOutput(boolean bonus) {
        System.out.println(bonusMenuOutput);
        if (bonus) {
            System.out.println(String.format(bonusMenuOutputFormatTrue, DrinkMenu.CHAMPAGNE.getName(), bonusMenuCount));
        }
        if (!bonus) {
            System.out.println(String.format(OutputFormatNone, DrinkMenu.NONE.getName()));
        }
    }

    public void viewBenefitOutput(boolean benefit, List<String> discount) {
        System.out.println(benefitOutput);
        if (benefit) {
            System.out.println(String.format(benefitOutputFormatTrue,
                    discount.get(0), discount.get(1), discount.get(2), discount.get(3)));
        }
        if (!benefit) {
            System.out.println(String.format(OutputFormatNone, DrinkMenu.NONE.getName()));
        }
    }

    public void viewAllBenefitAmount(boolean benefit, String amount) {
        System.out.println(allBenefitAmount);
        if (benefit) {
            System.out.println(String.format(amountFormat, amount));
        }
        if (!benefit) {
            System.out.println(String.format(amountFormat, "0"));
        }
    }

    public void viewAmountOfPayment(String payment) {
        System.out.println(amountOfPayment);
        System.out.println(String.format(amountFormat, payment));
    }

    public void viewBadge(String badgeName) {
        System.out.println(badge);
        System.out.println(badgeName);
    }
}
