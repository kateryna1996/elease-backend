package nl.klev.eleasebackend.controllers;

import nl.klev.eleasebackend.dtos.AccountDto;
import nl.klev.eleasebackend.dtos.AccountInputDto;
import nl.klev.eleasebackend.services.AccountService;
import nl.klev.eleasebackend.utilities.ErrorReport;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("")
    public ResponseEntity<Object> addAccount(@Valid @RequestBody AccountInputDto accountInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            AccountDto accountDto = accountService.createAccount(accountInputDto);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/accounts/" + accountDto.getAccountId()).toUriString());

            return ResponseEntity.created(uri).body(accountDto);
        }
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllAccounts(@RequestParam(value = "fullName", required = false) Optional<String> fullName) {
        if (fullName.isEmpty()) {
            List<AccountDto> accountDtoList = accountService.getAllAccounts();
            return ResponseEntity.ok().body(accountDtoList);
        } else {
            AccountDto foundAccount = accountService.getAccountByName(String.valueOf(fullName));
            return ResponseEntity.ok().body(foundAccount);
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("accountId") Long accountId) {
        AccountDto foundAccountDto = accountService.getAccountById(accountId);
        return ResponseEntity.ok().body(foundAccountDto);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Object> updateAccountDetails(@PathVariable Long accountId, @Valid @RequestBody AccountInputDto accountInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            AccountDto accountDto = accountService.updateAccountInformation(accountId, accountInputDto);
            return ResponseEntity.ok().body(accountDto);
        }
    }

    @DeleteMapping("/{fullName}")
    public ResponseEntity<Object> deleteAccountByUserName(@PathVariable("fullName") String fullName) {
        accountService.deleteAccountByName(fullName);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{accountId}/user")
    public ResponseEntity<Object> assignAccountToUser(@PathVariable("accountId") Long accountId, @RequestBody String username) {
        accountService.assignUserToAccount(accountId, username);
        return ResponseEntity.ok().body("Done!");
    }

    @PutMapping("/{accountId}/membership")
    public void assignMembershipToAccount(@PathVariable("accountId") Long accountId, @RequestBody Long membershipId) {
        accountService.assignMembershipToAccount(accountId, membershipId);
    }

    @PutMapping("/{accountId}/vehicle")
    public ResponseEntity<?> assignVehicleToAccount(@PathVariable("accountId") Long accountId, @RequestBody Long vehicleId) {
        accountService.assignVehicleToAccount(accountId, vehicleId);
        return ResponseEntity.noContent().build();
    }
}
