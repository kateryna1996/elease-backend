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
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtoList = accountService.getAllAccounts();

        return ResponseEntity.ok().body(accountDtoList);
    }

    @GetMapping("/{name}")
    public ResponseEntity<AccountDto> getAccountByName(@PathVariable("name") String name) {
        AccountDto foundAccount = accountService.getAccountByName(name);

        return ResponseEntity.ok().body(foundAccount);
    }


    @DeleteMapping("/{name}")
    public ResponseEntity deleteAccountByUserName(@PathVariable("name") String name) {
        accountService.deleteAccountByName(name);
        return ResponseEntity.noContent().build();
    }
}
