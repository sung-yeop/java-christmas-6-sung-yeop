package christmas.view;

import christmas.domain.Badge;
import christmas.domain.Menu;

import java.text.DecimalFormat;
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
    private final static String allBenefitAmount = "<총혜택 금액>";
    private final static String amountOfPayment = "<할인 후 예상 결제 금액>";
    private final static String amountFormat = "-%s원\n";
    private final static String badgeOutput = "<12월 이벤트 배지>";
    private final static String benefitOutputFormat = "%s: -%s원\n";
    private final static int bonusMenuCount = 1;
    private DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public void viewMenuOutput(Map<String, Integer> orderMenu) {
        System.out.println(menuOutput);
        String menuOutput = orderMenu.keySet().stream().map(k -> String.format(menuOutputFormat, k, orderMenu.get(k)))
                .collect(Collectors.joining());
        System.out.println(menuOutput);
    }

    public void viewOrderAmountOutput(int amount) {
        System.out.println(amountOutput);
        System.out.println(String.format(amountOutputFormat, decimalFormat.format(amount)));
    }

    public void viewBonusMenuOutput(boolean bonus) {
        System.out.println(bonusMenuOutput);
        if (bonus) {
            System.out.println(String.format(bonusMenuOutputFormatTrue, Menu.CHAMPAGNE.getName(), bonusMenuCount));
        }
        if (!bonus) {
            System.out.println(String.format(OutputFormatNone, Menu.NONE.getName()));
        }
    }

    public void viewBenefitOutput(boolean benefit, Map<String, Integer> discountTotal) {
        System.out.println(benefitOutput);
        String out = "";
        if (benefit) {
            for (String input : discountTotal.keySet()) {
                out += String.format(benefitOutputFormat, input, decimalFormat.format(discountTotal.get(input)));
            }
            System.out.println(out);
        }
        if (!benefit) {
            System.out.println(String.format(OutputFormatNone, Menu.NONE.getName()));
        }
    }

    public void viewAllBenefitAmount(boolean benefit, int amount) {
        System.out.println(allBenefitAmount);
        if (benefit) {
            System.out.println(String.format(amountFormat, decimalFormat.format(amount)));
        }
        if (!benefit) {
            System.out.println(String.format(amountFormat, "0"));
        }
    }

    public void viewAmountOfPayment(int payment) {
        System.out.println(amountOfPayment);
        System.out.println(String.format(amountFormat, decimalFormat.format(payment)));
    }

    public void viewBadge(Badge badgeName) {
        System.out.println(badgeOutput);
        System.out.println(badgeName.getName());
    }
}
