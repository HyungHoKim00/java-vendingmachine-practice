package vendingmachine.model;

import java.util.HashMap;
import java.util.Map;

public class Products {
    private static final String NO_PRODUCT = "[ERROR] 해당 제품은 존재하지 않습니다.";
    private static final String NOT_ENOUGH_MONEY = "[ERROR] 잔고 부족";
    private final Map<String, int[]> productNameAndDetails;

    public Products(String[] products) {
        productNameAndDetails = new HashMap<>();
        for (int i = 0; i < products.length; i++) {
            String[] productDetail = products[i].split(",");
            String productName = productDetail[0].substring(1);
            int[] productDetails = new int[2];
            productDetails[0] = Integer.parseInt(productDetail[1]);
            productDetails[1] = Integer.parseInt(productDetail[2].split("]")[0]);
            productNameAndDetails.put(productName, productDetails);
        }
    }

    public boolean ableToBuy(int money) {
        return productNameAndDetails.values().stream()
                .anyMatch(details -> details[0] < money)
                && productNameAndDetails.values().stream()
                .anyMatch(details -> details[1] > 0);
    }

    public int buy(String purchaseProduct, int userMoney) {
        if (noSuchProduct(purchaseProduct)) {
            throw new IllegalArgumentException(NO_PRODUCT);
        }
        if (productNameAndDetails.get(purchaseProduct)[0] > userMoney) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY);
        }
        productNameAndDetails.get(purchaseProduct)[1]--;
        return productNameAndDetails.get(purchaseProduct)[0];
    }

    private boolean noSuchProduct(String product) {
        return !productNameAndDetails.containsKey(product) && productNameAndDetails.get(product)[1] == 0;
    }
}
