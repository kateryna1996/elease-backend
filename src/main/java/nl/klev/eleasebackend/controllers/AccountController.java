package nl.klev.eleasebackend.controllers;

import nl.klev.eleasebackend.dtos.AccountDto;
import nl.klev.eleasebackend.dtos.AccountInputDto;
import nl.klev.eleasebackend.dtos.IdInputDto;
import nl.klev.eleasebackend.services.AccountService;
import nl.klev.eleasebackend.utilities.ErrorReport;
import nl.klev.eleasebackend.utilities.WriteToFile;
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
    public ResponseEntity<Object> getAllAccounts(@RequestParam(value = "fullname", required = false)Optional <String> fullname) {
        if (fullname.isEmpty()) {
            List<AccountDto> accountDtoList = accountService.getAllAccounts();
            return ResponseEntity.ok().body(accountDtoList);
        } else {
            AccountDto foundAccount = accountService.getAccountByName(fullname.get());
            return ResponseEntity.ok().body(foundAccount);
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("accountId") Long accountId){
        AccountDto foundAccountDto = accountService.getAccountById(accountId);
        return ResponseEntity.ok().body(foundAccountDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAccountDetails(@PathVariable Long id, @Valid @RequestBody AccountInputDto accountInputDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorReport.reportError(bindingResult));
        } else {
            AccountDto accountDto = accountService.updateAccountInformation(id, accountInputDto);
            return ResponseEntity.ok().body(accountDto);
        }
    }


    @DeleteMapping("/{name}")
    public ResponseEntity <Object> deleteAccountByUserName(@PathVariable("name") String name) {
        accountService.deleteAccountByName(name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteById/{accountId}")
    public ResponseEntity<Object> deleteAccountById(@PathVariable Long accountId) {
        accountService.deleteAccountById(accountId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/membership")
    public void assignMembershipToAccount(@PathVariable("id") Long accountId, @RequestBody IdInputDto membershipId) {
        accountService.assignMembershipToAccount(accountId, membershipId.id);
    }

    @PutMapping("/{id}/user")
    public ResponseEntity<Object> assignAccountToUser(@PathVariable("id") Long id, @RequestBody IdInputDto accountId){
        accountService.assignUserToAccount(id,accountId.id);
        return ResponseEntity.ok().body("Done!");
    }
    @PutMapping("/{id}/vehicle")
    public ResponseEntity assignVehicleToAccount(@PathVariable("id") Long id, @RequestBody IdInputDto vehicleId) {
        accountService.assignVehicleToAccount(id,vehicleId.id);
        return ResponseEntity.noContent().build();
    }
}
