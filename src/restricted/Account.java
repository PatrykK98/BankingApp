
package restricted;

import ExceptionClasses.NonSufficientFundsException;
import ExceptionClasses.ReachedCreditLimitException;

import java.math.BigDecimal;

public abstract class Account {
	private String number;
	private BigDecimal balance = BigDecimal.ZERO;

	Account(String number) {
		this.number = number;
	}

	void topUp(BigDecimal amount) {
		balance = getBalance().add(amount);
	}

	abstract BigDecimal withdraw(BigDecimal amount) throws NonSufficientFundsException, ReachedCreditLimitException;

	abstract BigDecimal recalculatePercents(BigDecimal percent);

	String getNumber() {
		return number;
	}

	BigDecimal getBalance() {
		return balance;
	}

	void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [number=" + number + ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
}
