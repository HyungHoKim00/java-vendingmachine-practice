package vendingmachine.controller;

import vendingmachine.model.Change;
import vendingmachine.model.Products;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachine {
    private final InputView inputView;
    private final OutputView outputView;
    private Products products;
    private int userMoney;

    public VendingMachine(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Change change = validateChange();
        outputView.printGeneratedChange(change.generateTotalCoinsOutput());
        this.products = validateProducts();
        this.userMoney = validateUserMoney();
        outputView.printUserMoneyRemain(userMoney);
        while (products.ableToBuy(userMoney)) {
            outputView.printProductNameRequestMessage();
            buy();
            outputView.printUserMoneyRemain(userMoney);
        }
        outputView.printChangeGiven(change.generateChangeOutput(userMoney));
    }


    private void buy() {
        while (true) {
            try {
                String purchaseProduct = inputView.readPurchase();
                userMoney = userMoney - products.buy(purchaseProduct, userMoney);
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Change validateChange() {
        outputView.printChangeRequestMessage();
        int change;
        while (true) {
            try {
                change = inputView.readMoney();
                return new Change(change);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Products validateProducts() {
        outputView.printProductsRequestMessage();
        String[] products;
        while (true) {
            try {
                products = inputView.readProducts();
                return new Products(products);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int validateUserMoney() {
        outputView.printUserMoneyRequestMessage();
        int userMoney;
        while (true) {
            try {
                userMoney = inputView.readMoney();
                return userMoney;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
