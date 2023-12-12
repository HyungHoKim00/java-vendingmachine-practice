package vendingmachine.view;

public class OutputView {
    public void printChangeRequestMessage() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
    }

    public void printGeneratedChange(String coins) {
        System.out.println();
        System.out.println("자판기가 보유한 동전");
        System.out.println(coins);
    }

    public void printProductsRequestMessage() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
    }

    public void printUserMoneyRequestMessage() {
        System.out.println();
        System.out.println("투입 금액을 입력해 주세요.");
    }

    public void printUserMoneyRemain(int userMoney) {
        System.out.println();
        System.out.println("투입 금액: " + userMoney + "원");
    }

    public void printProductNameRequestMessage() {
        System.out.println("구매할 상품명을 입력해 주세요");
    }

    public void printChangeGiven(String change) {
        System.out.println("잔돈");
        System.out.println(change);
    }

}
