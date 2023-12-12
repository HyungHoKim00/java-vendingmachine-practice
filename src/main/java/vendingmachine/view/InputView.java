package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String POSITIVE_INTEGER_PATTERN = "^[1-9]\\d*$";
    private static final String ERR_MSG_NOT_NUMBER = "[ERROR] 입력이 숫자가 아닙니다.";
    private static final String ERR_MSG_INVALID_UNIT = "[ERROR] 입력 단위가 올바르지 않습니다.";
    private static final int MONEY_UNIT = 10;

    public int readMoney() {
        String input = Console.readLine();
        validateMoney(input);
        return Integer.parseInt(input);
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
}
