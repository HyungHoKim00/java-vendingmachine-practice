package vendingmachine;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public static Map<Coin, Integer> generateChangeCoins(int money) {
        Map<Coin, Integer> changeCoins = new EnumMap<>(Coin.class);
        generateCoinMap(changeCoins);
        List<Integer> coinAmounts = Arrays.stream(values())
                .map(Coin::getAmount)
                .collect(Collectors.toList());
        putRandomCoins(money, changeCoins, coinAmounts);
        return changeCoins;
    }
    
    public static Coin getCoin(int money) {
        return Arrays.stream(values())
                .filter(coin -> coin.amount == money)
                .findFirst()
                .orElse(COIN_10);
    }

    private static void putRandomCoins(int money, Map<Coin, Integer> changeCoins, List<Integer> coinAmounts) {
        while (money != 0) {
            if (money < 50) {
                changeCoins.replace(COIN_10, changeCoins.get(COIN_10) + money / 10);
                break;
            }
            int randomAmount = Randoms.pickNumberInList(coinAmounts);
            if (money - randomAmount >= 0) {
                Coin randomCoin = getCoin(randomAmount);
                changeCoins.replace(randomCoin, changeCoins.get(randomCoin) + 1);
                money = money - randomAmount;
            }
        }
    }

    private static void generateCoinMap(Map<Coin, Integer> changeCoins) {
        changeCoins.put(COIN_500, 0);
        changeCoins.put(COIN_100, 0);
        changeCoins.put(COIN_50, 0);
        changeCoins.put(COIN_10, 0);
    }
}
