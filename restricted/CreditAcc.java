package restricted;

import java.math.BigDecimal;

import ExceptionClasses.ReachedCreditLimitException;

public class CreditAcc extends Account {
	BigDecimal limit;

	public CreditAcc(String number, BigDecimal limit) {
		super(number);
		this.limit=limit;
	}
	@Override
	BigDecimal withdraw(BigDecimal amount) throws ReachedCreditLimitException  {
		balance = balance.subtract(amount);
		if (balance.compareTo(limit) == 0){
			throw new ReachedCreditLimitException("You reached the limit which is: "+limit);
		}
		if (balance.compareTo(limit) == -1){
			balance = balance.add(amount);
			throw new ReachedCreditLimitException("Can't make the transaction bacause you reached the limit, which is: "+limit+"\nYour balance is: "+balance);
		}
		if (balance.compareTo(limit) == 1) {
			System.out.println("Your balance is: ");
			return balance;
		}
		return balance;
	}
	
	@Override
	BigDecimal recalculatePercents(BigDecimal percent) {
		if(balance.compareTo(BigDecimal.ZERO) == 1) {
		return balance.subtract(balance.multiply(percent));
		}
		else if((balance.compareTo(BigDecimal.ZERO) == -1)){
			return balance.add(balance.multiply(percent));
		}
		return balance;
	}
	

}