package christmas.view;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private final static String newLine = "\n";
    private final static String menuOutput = "<주문 메뉴>";
    private final static String menuOutputFormat = "%s %d개\n";
    private final static String amountOutput = "<할인 전 총주문 금액>\n";
    private final static String amountOutputFormat = "%s원\n";
    private final static String bonusMenuOutput = "<증정 메뉴>\n";
    private final static String bonusMenuOutputFormat = "%s %d개\n";
    private final static String benefitOutput = "<혜택 내역>\n";
    private final static String benefitOutputFormat = "크리스마스 디데이 할인: -%s원\n" + "평일 할인: -%s원\n" +
            "특별 할인: -%s원\n" + "증정 이벤트: -%s원\n";
    private final static String allBenefitAmount = "<총혜택 금액>";
    private final static String amountOfPayment = "<할인 후 예상 결제 금액>";
    private final static String amountFormat = "-%s원";

    public void viewMenuOutput(Map<String, Integer> orderMenu) {
        System.out.println(menuOutput);
        String menuOutput = orderMenu.keySet().stream().map(k -> String.format(menuOutputFormat, k, orderMenu.get(k)))
                .collect(Collectors.joining());
        System.out.println(menuOutput);
    }
}
