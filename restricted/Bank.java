package restricted;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.AccountNotFoundException;

import ExceptionClasses.NonSufficientFundsException;
import ExceptionClasses.ReachedCreditLimitException;

public class Bank {
	private String name;
	private Account account;

	public Bank(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	final Map<String, Account> accountList = new HashMap<>();

	public void registerDebitAccount(Account a) {
		accountList.put(a.getNumber(), a);
	}

	public void registerCreditAccount(Account a) {
		accountList.put(a.getNumber(), a);
	}

	public void topUp(String accountNumber, BigDecimal amount) throws AccountNotFoundException {
		if (!accountList.containsKey(accountNumber)) {
			throw new AccountNotFoundException("Account doesn't exist");
		}
		accountList.get(accountNumber).topUp(amount);
	}

	public BigDecimal withdraw(String accountNumber, BigDecimal amount)
			throws NonSufficientFundsException, ReachedCreditLimitException, AccountNotFoundException {
		if (!accountList.containsKey(accountNumber)) {
			throw new AccountNotFoundException("Account doesn't exist");
		}
		return accountList.get(accountNumber).withdraw(amount);

	}

	public void transfer(String sourceAccount, BigDecimal amount, String targetAccount, Bank bank)
			throws NonSufficientFundsException, ReachedCreditLimitException, AccountNotFoundException {
		if (!accountList.containsKey(sourceAccount)) {
			throw new AccountNotFoundException("Account doesn't exist");

		}
		accountList.get(sourceAccount).withdraw(amount);
		bank.topUp(targetAccount, amount);
		System.out.println(bank.toString());

	}

	public BigDecimal recalculatePercents(String accountNumber, BigDecimal percents) throws AccountNotFoundException {
		if (!accountList.containsKey(accountNumber)) {
			throw new AccountNotFoundException("Account doesn't exist");
		}
		return accountList.get(accountNumber).recalculatePercents(percents);
	}

	@Override
	public String toString() {
		return "Bank [name=" + name + accountList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Bank other = (Bank) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
