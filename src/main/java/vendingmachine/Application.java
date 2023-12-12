package vendingmachine;

import vendingmachine.controller.VendingMachine;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Application {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine(new InputView(), new OutputView());
        vendingMachine.run();
    }
}
