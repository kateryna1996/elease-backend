package nl.klev.eleasebackend.services;

import nl.klev.eleasebackend.dtos.AccountDto;
import nl.klev.eleasebackend.dtos.AccountInputDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.exceptions.UserNotFoundException;
import nl.klev.eleasebackend.models.Account;
import nl.klev.eleasebackend.repositories.AccountRepository;
import nl.klev.eleasebackend.repositories.MembershipRepository;
import nl.klev.eleasebackend.repositories.UserRepository;
import nl.klev.eleasebackend.repositories.VehicleRepository;
import nl.klev.eleasebackend.utilities.AccountTransform;
import nl.klev.eleasebackend.utilities.WriteToFile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public AccountService(AccountRepository accountRepository, MembershipRepository membershipRepository, UserRepository userRepository, VehicleRepository vehicleRepository) {
        this.accountRepository = accountRepository;
        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
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

    //adding filters?
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
        if (foundAccount == null) {
            throw new UserNotFoundException(name);
        } else {
            foundAccountDto = AccountTransform.toAccountDto(foundAccount);
        }
        return foundAccountDto;
    }

    public AccountDto getAccountById(Long accountId) {
        AccountDto accountDto = new AccountDto();
        Optional<Account> foundAccount = accountRepository.findById(accountId);
        if (foundAccount.isPresent()) {
            accountDto = AccountTransform.toAccountDto(foundAccount.get());
            WriteToFile.toFile(foundAccount.get());
        } else {
            throw new RecordNotFoundException("The account with the id " + accountId + " cannot be found!");
        }
        return accountDto;
    }

    public AccountDto updateAccountInformation(Long accountId, AccountInputDto accountInputDto) {
        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isPresent()) {
            Account newAccount = account.get();
            Account accountToSet = AccountTransform.toAccount(accountInputDto);
            accountToSet.setAccountId(newAccount.getAccountId());
            Account returningAccount = accountRepository.save(accountToSet);
            return AccountTransform.toAccountDto(accountToSet);
        } else {
            throw new RecordNotFoundException("The account was not found!");
        }
    }

    public void deleteAccountByName(String name) {
        Account foundAccount = accountRepository.findAccountByFullName(name);
        accountRepository.delete(foundAccount);
    }

    public void deleteAccountById(Long accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
        } else if (accountId < 0) {
            throw new RecordNotFoundException("The id cannot be negative, choose again");
        } else {
            throw new RecordNotFoundException("The account with this id does not exist!");
        }
    }

    public void assignMembershipToAccount(Long accountId, Long membershipId) {
        var account = accountRepository.findById(accountId);
        var membership = membershipRepository.findById(membershipId);

        if (membership.isPresent() && account.isPresent()) {
            var foundAccount = account.get();
            var foundMembership = membership.get();

            foundAccount.setMembership(foundMembership
            );
            accountRepository.save(foundAccount);
        } else if (membership.isEmpty()) {
            throw new RecordNotFoundException("Membership with this id has not been found!");
        } else if (account.isEmpty()) {
            throw new RecordNotFoundException("Account with this id has not been found!");
        } else {
            throw new RecordNotFoundException("Something went wrong!");
        }
    }

    public void assignUserToAccount(Long accountId, Long membershipId) {
        var account = accountRepository.findById(accountId);
        var user = userRepository.findById(membershipId);

        if (account.isPresent() && user.isPresent()) {
            var foundAccount = account.get();
            var foundUser = user.get();

            foundAccount.setUser(foundUser);
            accountRepository.save(foundAccount);
        } else if (user.isEmpty()) {
            throw new RecordNotFoundException("User with the id has not been found!");
        } else {
            throw new RecordNotFoundException("Account with this id has not been found!");
        }
    }

    public void assignVehicleToAccount(Long accountId, Long vehicleId) {
        var account = accountRepository.findById(accountId);
        var vehicle = vehicleRepository.findById(vehicleId);

        if (account.isPresent() && vehicle.isPresent()) {
            var foundAccount = account.get();
            var foundVehicle = vehicle.get();

            foundAccount.setVehicle(foundVehicle);
            accountRepository.save(foundAccount);
        } else if (vehicle.isEmpty()) {
            throw new RecordNotFoundException("Vehicle with this id has not been found!");
        } else if (account.isEmpty()){
            throw new RecordNotFoundException("Membership with this id has not been found!");
        } else {
            throw new RecordNotFoundException("Something went wrong!");
        }
    }

    public boolean accountWithDrivingLicenseExists(int drivingLicense) {
        Optional<Account> foundAccount = Optional.ofNullable(accountRepository.findAccountByDrivingLicenseNumber(drivingLicense));
        return foundAccount.isPresent();
    }
}
