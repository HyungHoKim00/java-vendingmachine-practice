package vendingmachine.model;

import static vendingmachine.Coin.COIN_10;
import static vendingmachine.Coin.COIN_100;
import static vendingmachine.Coin.COIN_50;
import static vendingmachine.Coin.COIN_500;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import vendingmachine.Coin;

public class Change {
    private Map<Coin, Integer> coins;

    public Change(int money) {
        this.coins = Coin.generateChangeCoins(money);
    }

    public Map<Coin, Integer> giveChange(int userMoney) {
        Map<Coin, Integer> change = new EnumMap<>(Coin.class);
        if (isFullChange(userMoney)) {
            return coins;
        }
        int after500 = getCoinAmountForChange(change, COIN_500, userMoney);
        int after100 = getCoinAmountForChange(change, COIN_100, after500);
        int after50 = getCoinAmountForChange(change, COIN_50, after100);
        getCoinAmountForChange(change, COIN_10, after50);
        return change;
    }

    private int getCoinAmountForChange(Map<Coin, Integer> change, Coin coin, int userMoney) {
        int neededCoin = userMoney / coin.getAmount();
        if (neededCoin > coins.get(coin)) {
            change.put(coin, coins.get(coin));
            return userMoney - coin.getAmount() * coins.get(coin);
        }
        change.put(coin, neededCoin);
        return userMoney - neededCoin * coin.getAmount();
    }

    private boolean isFullChange(int userMoney) {
        AtomicInteger totalChange = new AtomicInteger();
        coins.keySet().forEach(key -> totalChange.set(totalChange.get() + key.getAmount() * coins.get(key)));
        return userMoney >= totalChange.get();
    }
}