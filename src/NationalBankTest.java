import ExceptionClasses.BankNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NationalBankTest {

    @Test
    void getByName_providedWrongName_BankNotFoundException(){
        BankNotFoundException exception = assertThrows(BankNotFoundException.class,
                () -> NationalBank.getByName("Alior"));
        assertEquals("Couldn't find the bank", exception.getMessage());
    }

}