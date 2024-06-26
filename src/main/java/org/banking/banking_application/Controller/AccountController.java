package org.banking.banking_application.Controller;

import org.banking.banking_application.Dto.AccountDto;
import org.banking.banking_application.Services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {

    private AccountService accountService;


    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    //Add account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }


    //Get account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long id) throws AccountNotFoundException {
        AccountDto accountDto = accountService.findAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //Put deposit amount in request account
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long id,
                                              @RequestBody Map<String,Double> request)
            throws AccountNotFoundException {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);

    }


    //Put withdraw amount in request account
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable("id") Long id,
                                              @RequestBody Map<String,Double> request)
            throws AccountNotFoundException {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);

    }


    //Get all the account in database
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtoList = accountService.getsAllAccounts();
        return ResponseEntity.ok(accountDtoList);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long id)
            throws AccountNotFoundException {
       accountService.deleteAccount(id);
       return ResponseEntity.ok("Account deleted successfully");
    }


}
