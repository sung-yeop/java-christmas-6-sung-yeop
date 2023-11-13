package christmas.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeEventTest {

    private BadgeEvent badgeEvent;

    @BeforeEach
    void setBadgeEvent() {
        badgeEvent = new BadgeEvent();
    }

    @DisplayName("구입 금액에 따른 뱃지 이름 반환")
    @Test
    void 뱃지_이름_반환_테스트() {
        int santa = 20000;
        int tree = 10000;
        int star = 6000;

        Assertions.assertEquals(badgeEvent.giveBadge(santa), "산타");
        Assertions.assertEquals(badgeEvent.giveBadge(tree), "트리");
        Assertions.assertEquals(badgeEvent.giveBadge(star), "별");
    }


}