package vendingmachine.model;

import static vendingmachine.Coin.COIN_10;
import static vendingmachine.Coin.COIN_100;
import static vendingmachine.Coin.COIN_50;
import static vendingmachine.Coin.COIN_500;

import java.util.EnumMap;
import java.util.Map;
import vendingmachine.Coin;

public class Change {
    private final Map<Coin, Integer> coins;

    public Change(int money) {
        this.coins = Coin.generateChangeCoins(money);
    }

    public String generateTotalCoinsOutput() {
        return generateCoinsOutput(coins);
    }

    public String generateChangeOutput(int userMoney) {
        return generateCoinsOutput(giveChange(userMoney));
    }

    private String generateCoinsOutput(Map<Coin, Integer> change) {
        StringBuilder output = new StringBuilder();
        appendString(COIN_500, change, output);
        appendString(COIN_100, change, output);
        appendString(COIN_50, change, output);
        appendString(COIN_10, change, output);
        return output.toString();
    }

    private void appendString(Coin coin, Map<Coin, Integer> change, StringBuilder output) {
        if (change.containsKey(coin)) {
            output.append(coin.getAmount()).append("원 - ").append(change.get(coin)).append("개\n");
        }
    }

    public Map<Coin, Integer> giveChange(int userMoney) {
        Map<Coin, Integer> change = new EnumMap<>(Coin.class);
        int after500 = getCoinAmountForChange(change, COIN_500, userMoney);
        int after100 = getCoinAmountForChange(change, COIN_100, after500);
        int after50 = getCoinAmountForChange(change, COIN_50, after100);
        getCoinAmountForChange(change, COIN_10, after50);
        return change;
    }

    private int getCoinAmountForChange(Map<Coin, Integer> change, Coin coin, int userMoney) {
        int neededCoin = userMoney / coin.getAmount();
        if (neededCoin == 0) {
            return userMoney;
        }
        if (neededCoin > coins.get(coin)) {
            change.put(coin, coins.get(coin));
            return userMoney - coin.getAmount() * coins.get(coin);
        }
        change.put(coin, neededCoin);
        return userMoney - neededCoin * coin.getAmount();
    }
}
