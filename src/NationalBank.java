import ExceptionClasses.BankNotFoundException;
import restricted.Bank;

import java.util.HashMap;
import java.util.Map;

public class NationalBank {
	public NationalBank() {
	}
	private static final Map<String,Bank> bankList = new HashMap<>();

	static void registerBank(Bank bank) {
    	bankList.put(bank.getName(),bank);
    }

	  static Bank getByName(String bankName) throws BankNotFoundException{	
		  	Bank object =bankList.get(bankName);
	    	if(!bankList.containsKey(bankName)) {
	    		throw new BankNotFoundException("Couldn't find the bank");
	    	}
	        return object;
	    }
}
