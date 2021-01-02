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
		setBalance(getBalance().subtract(amount)); 
		if (getBalance().compareTo(limit) == 0){
			System.out.println("You reached the limit which is: "+limit);
		}
		if (getBalance().compareTo(limit) == -1){
			setBalance(getBalance().add(amount));
			throw new ReachedCreditLimitException("Can't make the transaction bacause you reached the limit, which is: "+limit+"\nYour balance is: "+getBalance());
		}
		if (getBalance().compareTo(limit) == 1) {
			System.out.println("Your balance is: "+getBalance());
		}
		return getBalance();
	}
	
	@Override
	BigDecimal recalculatePercents(BigDecimal percent) {
		if(getBalance().compareTo(BigDecimal.ZERO) == 1) {
		setBalance(getBalance().subtract(getBalance().multiply(percent)));
		return getBalance();
		}
		else if((getBalance().compareTo(BigDecimal.ZERO) == -1)){
			 setBalance(getBalance().add(getBalance().multiply(percent)));
			 return getBalance();
		}
		return getBalance();
	}
	

}
