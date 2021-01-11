import java.math.BigDecimal;

import Exceptions.BankNotFoundException;
import Exceptions.NonSufficientFundsException;
import Exceptions.ReachedCreditLimitException;
import restricted.Account;
import restricted.Bank;
import restricted.CreditAcc;
import restricted.DebitAcc;

import javax.security.auth.login.AccountNotFoundException;

public class BankApp {
	public static void main(String[] args) {
		Bank lloyds = new Bank("Lloyds");
		Bank alior = new Bank("Alior");
		Account personal = new CreditAcc("12345", new BigDecimal("-50"));
		Account corporate = new DebitAcc("54321");
		lloyds.registerCreditAccount(personal);
		alior.registerDebitAccount(corporate);
		NationalBank.registerBank(lloyds);
		NationalBank.registerBank(alior);
		try {
			lloyds.topUp("12345", new BigDecimal("100"));
			alior.topUp("54321", new BigDecimal("100"));
			//System.out.println(lloyds.withdraw("12345", new BigDecimal("1")));
			System.out.println(alior.withdraw("54321", new BigDecimal("46")));
		} catch (NonSufficientFundsException e) {
			System.out.println(e.getMessage());
		} catch (ReachedCreditLimitException a) {
			System.out.println(a.getMessage());
		}catch(AccountNotFoundException b) {
			System.out.println(b.getMessage());
		}
		try {
			System.out.println(NationalBank.getByName("Alior"));
		} catch (BankNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	}

