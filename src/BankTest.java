import ExceptionClasses.NonSufficientFundsException;
import ExceptionClasses.ReachedCreditLimitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restricted.Account;
import restricted.Bank;
import restricted.CreditAcc;
import restricted.DebitAcc;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankTest {

    Bank bank1 = new Bank("Stefczyk");
    Account acc1 = new DebitAcc("1245");


    @BeforeEach
    public void setUp() {
        bank1.registerDebitAccount(acc1);
        NationalBank.registerBank(bank1);

    }


    @Test
    void topUp_WrongAcc_throwsAccountNotFoundException() {
        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class,
                () -> bank1.topUp("1246", new BigDecimal("50")));
        assertEquals("Account doesn't exist", exception.getMessage());
    }

    @Test
    void topUp_BalanceEquals50() throws AccountNotFoundException{
        bank1.topUp("1245", new BigDecimal("50"));
        assertEquals("Account [number=1245, balance=50]", acc1.toString());
    }

    @Test
    void withDraw_NotEnoughMoney_throwsNonSufficientFundsException() {
        NonSufficientFundsException exception = assertThrows(NonSufficientFundsException.class,
                () -> bank1.withdraw("1245", new BigDecimal("60")));
        assertEquals("You can't make the transaction because you do not have enough money.", exception.getMessage());
    }

    @Test
    void withDraw_BalanceEquals10() throws NonSufficientFundsException, ReachedCreditLimitException, AccountNotFoundException {
        bank1.topUp("1245", new BigDecimal("50"));
        bank1.withdraw("1245", new BigDecimal("40"));
        assertEquals("Account [number=1245, balance=10]", acc1.toString());
    }

    @Test
    void transfer_AfterTransferRemains50() throws NonSufficientFundsException, ReachedCreditLimitException, AccountNotFoundException{
        Account acc2 = new CreditAcc("2356", new BigDecimal("0"));
        bank1.registerCreditAccount(acc2);
        bank1.topUp("1245", new BigDecimal("100"));
        bank1.transfer("1245", new BigDecimal("50"), "2356", bank1);
        assertEquals("Account [number=1245, balance=50]", acc1.toString());
    }

    @Test
    void transfer_NotEnoughMoney_throwsReachedCreditLimitException() throws AccountNotFoundException {
        Account acc2 = new CreditAcc("2356", new BigDecimal("-10"));
        bank1.registerCreditAccount(acc2);
        bank1.topUp("2356", new BigDecimal("100"));
        ReachedCreditLimitException exception = assertThrows(ReachedCreditLimitException.class,
                () -> bank1.transfer("2356", new BigDecimal(150), "1245", bank1));
        assertEquals("Can't make the transaction because you reached the limit",exception.getMessage());
    }
}