package restricted;

import java.math.BigDecimal;

import ExceptionClasses.NonSufficientFundsException;

public class DebitAcc extends Account {
	private BigDecimal limit = BigDecimal.ZERO;

	public DebitAcc(String number) {
		super(number);
	}

	@Override
	BigDecimal withdraw(BigDecimal amount) throws NonSufficientFundsException {
		balance = balance.subtract(amount);
		if (balance.compareTo(limit) == -1) {
			balance = balance.add(amount);
			throw new NonSufficientFundsException(
					"You can't make the transaction beacause you do not have enough money \nYour balance is: "
							+ balance);
		}

		if (balance.compareTo(limit) == 0) {
			throw new NonSufficientFundsException("Your balance equals: " + balance);
		}
		if (balance.compareTo(limit) == 1) {
			System.out.println("Your balance is: ");
		}
		return balance;
	}

	@Override
	BigDecimal recalculatePercents(BigDecimal percent) {
		BigDecimal value = balance.subtract(balance.multiply(percent));
		if (balance.compareTo(BigDecimal.ZERO) == 1) {
			return balance.subtract(balance.multiply(percent));
		}
		return balance;

	}
}
