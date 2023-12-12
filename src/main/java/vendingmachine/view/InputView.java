package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readMoney() {
        String input = Console.readLine();
        InputValidator.validateMoney(input);
        return Integer.parseInt(input);
    }

    public String[] readProducts() {
        String input = Console.readLine();
        String[] products = input.split(";");
        InputValidator.validateProducts(products);
        return products;
    }

    public String readPurchase() {
        return Console.readLine();
    }


}
