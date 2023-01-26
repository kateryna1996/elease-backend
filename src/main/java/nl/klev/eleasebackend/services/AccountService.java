package nl.klev.eleasebackend.services;

import nl.klev.eleasebackend.dtos.AccountDto;
import nl.klev.eleasebackend.dtos.AccountInputDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.exceptions.UserNotFoundException;
import nl.klev.eleasebackend.models.Account;
import nl.klev.eleasebackend.repositories.AccountRepository;
import nl.klev.eleasebackend.utilities.AccountTransform;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDto createAccount(AccountInputDto accountInputDto) {
//        add validation, no repeat values
        AccountDto accountDto = new AccountDto();
        Account createdAccount = AccountTransform.toAccount(accountInputDto);
        int numberToCheck = createdAccount.getDrivingLicenseNumber();
        if (accountWithDrivingLicenseExists(numberToCheck)) {
            throw new RecordNotFoundException("The account with this driving license already exists!");
        } else {
            accountRepository.save(createdAccount);
//        delete later
            accountDto = AccountTransform.toAccountDto(createdAccount);
            return accountDto;
        }
    }

    public List<AccountDto> getAllAccounts() {
        List<AccountDto> accountDtoList = new ArrayList<>();

        List<Account> accountList = accountRepository.findAll();
        for (Account account : accountList) {
            accountDtoList.add(AccountTransform.toAccountDto(account));
        }
        if (accountList.isEmpty()) {
            throw new RecordNotFoundException("The list of accounts is empty.");
        }
        return accountDtoList;
    }

    public AccountDto getAccountByName(String name) {
        AccountDto foundAccountDto = new AccountDto();
        Account foundAccount = accountRepository.findAccountByFullName(name);
        if(foundAccount == null) {
            throw  new UserNotFoundException(name);
        } else {
            foundAccountDto = AccountTransform.toAccountDto(foundAccount);
        }
        return foundAccountDto;
    }

    public String deleteAccountByName(String name) {
        Account foundAccount = accountRepository.findAccountByFullName(name);
        accountRepository.delete(foundAccount);

        return "Account of " + name + " is deleted";
    }

    public boolean accountWithDrivingLicenseExists(int drivingLicense) {
        Optional<Account> foundAccount = Optional.ofNullable(accountRepository.findAccountByDrivingLicenseNumber(drivingLicense));
        return foundAccount.isPresent();
    }
}
