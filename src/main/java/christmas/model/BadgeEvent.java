package christmas.model;

import christmas.domain.Badge;

import java.util.Arrays;
import java.util.Comparator;

public class BadgeEvent {
    public Badge giveBadge(int orderAmount) {
        return Arrays.stream(Badge.values()).sorted(Comparator.reverseOrder())
                .filter(b -> b.getCutLine() <= orderAmount).findFirst().orElse(Badge.NONE);
    }
}
