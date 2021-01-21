package restricted;

import ExceptionClasses.NonSufficientFundsException;

import java.math.BigDecimal;

public class DebitAcc extends Account {
	private BigDecimal limit = BigDecimal.ZERO;

	public DebitAcc(String number) {
		super(number);
	}

	@Override
	BigDecimal withdraw(BigDecimal amount) throws NonSufficientFundsException {
		setBalance(getBalance().subtract(amount));
		if (getBalance().compareTo(limit) == -1) {
			setBalance(getBalance().add(amount));
			throw new NonSufficientFundsException(
					"You can't make the transaction because you do not have enough money.");
		}

		if (getBalance().compareTo(limit) == 0) {
			throw new NonSufficientFundsException("Your balance equals: " + getBalance());
		}
		if (getBalance().compareTo(limit) == 1) {
			System.out.println("Your balance is: "+getBalance());
		}
		return getBalance();
	}

	@Override
	BigDecimal recalculatePercents(BigDecimal percent) {
		BigDecimal value = getBalance().subtract(getBalance().multiply(percent));
		if (getBalance().compareTo(BigDecimal.ZERO) == 1) {
			setBalance(getBalance().subtract(getBalance().multiply(percent)));
			 return getBalance();
		}
		return getBalance();
	}
}
