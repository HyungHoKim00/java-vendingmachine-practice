package vendingmachine.model;

import static org.assertj.core.api.Assertions.assertThat;
import static vendingmachine.Coin.COIN_10;
import static vendingmachine.Coin.COIN_500;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import vendingmachine.Coin;

public class ChangeTest {
    private static final Change VALID_BIG_CHANGE = new Change(100_000_000);
    private static final Change VALID_SMALL_CHANGE = new Change(20);

    @DisplayName("나머지가 보유금액보다 많을 시 전체를 거슬러준다")
    @Test
    void giveTotalChange() {
        int userMoney = 30;
        Map<Coin, Integer> expected = new EnumMap<>(Coin.class);

        Map<Coin, Integer> result = VALID_SMALL_CHANGE.giveChange(userMoney);
        expected.put(COIN_10, 2);

        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("나머지를 최소의 동전 수로 거슬러준다")
    @Test
    void giveChangeEfficiently() {
        int userMoney = 500;
        Map<Coin, Integer> expected = new EnumMap<>(Coin.class);

        Map<Coin, Integer> result = VALID_BIG_CHANGE.giveChange(userMoney);
        expected.put(COIN_500, 1);

        assertThat(result).isEqualTo(expected);
    }
}
