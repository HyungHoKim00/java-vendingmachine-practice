package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String POSITIVE_INTEGER_PATTERN = "^[1-9]\\d*$";
    private static final String ERR_MSG_NOT_NUMBER = "[ERROR] 입력이 숫자가 아닙니다.";
    private static final String ERR_MSG_INVALID_UNIT = "[ERROR] 입력 단위가 올바르지 않습니다.";
    private static final String ERR_MSG_INVALID_FORM = "[ERROR] 입력 형식이 올바르지 않습니다.";
    private static final int MONEY_UNIT = 10;

    public int readMoney() {
        String input = Console.readLine();
        validateMoney(input);
        return Integer.parseInt(input);
    }

    public String[] readProducts() {
        String input = Console.readLine();
        String[] products = input.split(";");
        validateProducts(products);
        return products;
    }

    public String readPurchase() {
        return Console.readLine();
    }

    private void validateMoney(String money) {
        if (!money.matches(POSITIVE_INTEGER_PATTERN)) {
            throw new IllegalArgumentException(ERR_MSG_NOT_NUMBER);
        }
        if (invalidMoneyUnit(money)) {
            throw new IllegalArgumentException(ERR_MSG_INVALID_UNIT);
        }
    }

    private boolean invalidMoneyUnit(String money) {
        return Integer.parseInt(money) % MONEY_UNIT != 0;
    }

    private void validateProducts(String[] products) {
        for (String product : products) {
            String[] productDetails = product.split(",");
            if (invalidDetailForm(productDetails)) {
                throw new IllegalArgumentException(ERR_MSG_INVALID_FORM);
            }
        }
    }

    private boolean invalidDetailForm(String[] productDetails) {
        if (productDetails.length != 3) {
            return false;
        }
        if (!productDetails[0].startsWith("[") || !productDetails[2].endsWith("]")) {
            return false;
        }
        productDetails[0] = productDetails[0].substring(1);
        productDetails[2] = productDetails[2].split("]")[0];
        if (!productDetails[1].matches(POSITIVE_INTEGER_PATTERN) || !productDetails[2].matches(
                POSITIVE_INTEGER_PATTERN)) {
            return false;
        }
        return invalidMoneyUnit(productDetails[1]) && invalidMoneyUnit(productDetails[2]);
    }
}
