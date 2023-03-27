package nl.klev.eleasebackend.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void shouldReturnProperAccountFields() {
        Account account = new Account();

        LocalDate date =LocalDate.of(1996,9,6);

        account.setAccountId(2L);
        account.setFullName("John Doe");
        account.setDob(date);
        account.setIban("7526954123456");
        account.setDrivingLicenseNumber(7542468);

        assertEquals(2L, account.getAccountId());
        assertEquals("John Doe", account.getFullName());
        assertEquals("1996-09-06", account.getDob().toString());
        assertEquals("7526954123456", account.getIban());
        assertEquals(7542468, account.getDrivingLicenseNumber());
    }

}