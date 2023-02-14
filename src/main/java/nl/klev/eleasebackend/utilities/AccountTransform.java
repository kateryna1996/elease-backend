package nl.klev.eleasebackend.utilities;

import nl.klev.eleasebackend.dtos.AccountDto;
import nl.klev.eleasebackend.dtos.AccountInputDto;
import nl.klev.eleasebackend.models.Account;

public class AccountTransform {

    public static Account toAccount(AccountInputDto accountInputDto) {
        var account = new Account();

        account.setFullName(accountInputDto.getFullName());
        account.setDob(accountInputDto.getDob());
        account.setIban(accountInputDto.getIban());
        account.setDrivingLicenseNumber(accountInputDto.getDrivingLicenseNumber());

        return account;
    }

    public static AccountDto toAccountDto(Account account) {
        var accountDto = new AccountDto();

        accountDto.setAccountId(account.getAccountId());
        accountDto.setFullName(account.getFullName());
        accountDto.setDob(account.getDob());
        accountDto.setIban(account.getIban());
        accountDto.setDrivingLicenseNumber(account.getDrivingLicenseNumber());

        if (account.getUser() != null) {
            accountDto.setUser(account.getUser());
        }
        if (account.getMembership() != null) {
            accountDto.setMembership(account.getMembership());
        }
        if (account.getVehicle() != null) {
            accountDto.setVehicle(account.getVehicle());
        }
        return accountDto;
    }
}
