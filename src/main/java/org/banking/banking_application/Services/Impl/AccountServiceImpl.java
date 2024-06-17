package org.banking.banking_application.Services.Impl;

import lombok.extern.slf4j.Slf4j;
import org.banking.banking_application.Converter.AccountConverter;
import org.banking.banking_application.Dto.AccountDto;
import org.banking.banking_application.Entity.Accounts;
import org.banking.banking_application.Repository.AccountRepo;
import org.banking.banking_application.Services.AccountService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Accounts accounts = AccountConverter.toAccounts(accountDto);
        accounts = accountRepo.save(accounts);
        return AccountConverter.toAccountDto(accounts);
    }

    @Override
    public AccountDto findAccountById(Long id) throws AccountNotFoundException {

        //By using the option type class

        Optional<Accounts> optionalAccounts = accountRepo.findById(id);

        if(optionalAccounts.isPresent()) {
            Accounts accounts = optionalAccounts.get();
            return AccountConverter.toAccountDto(accounts);
        }else {
            log.error("Invalid Id: {}", id);
            return null;
        }

        // this is optional type ...i.e if the account with id is not available then it throws an expection so we
        // use a lamda experssion to deal with it
//        Accounts accounts = accountRepo.findById(id).orElseThrow(() -> new AccountNotFoundException("Account does not exit !!!"));
//        return AccountConverter.toAccountDto(accounts);
    }

    @Override
    public AccountDto deposit(Long id, double amount) throws AccountNotFoundException {


//        Optional<Accounts> optionalAccounts = accountRepo.findById(id);
//        if (optionalAccounts.isPresent()) {
//            Accounts accounts = optionalAccounts.get();
//            accounts.setBalance(accounts.getBalance() + amount);
//            Accounts savedAccount = accountRepo.save(accounts);
//            return AccountConverter.toAccountDto(savedAccount);
//        }

        Accounts accounts = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        double total = accounts.getBalance() + amount;
        accounts.setBalance(total);
        Accounts updatedAccounts = accountRepo.save(accounts);
        return AccountConverter.toAccountDto(updatedAccounts);

    }

    @Override
    public AccountDto withdraw(Long id, double withAmount) throws AccountNotFoundException {
        Accounts accounts = accountRepo.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        if(accounts.getBalance()<withAmount) {
            throw new RuntimeException("Insufficient Balance");
        }

        double total = accounts.getBalance() - withAmount;
        accounts.setBalance(total);
        Accounts updatedAccounts = accountRepo.save(accounts);
        return AccountConverter.toAccountDto(updatedAccounts);

    }

    @Override
    public List<AccountDto> getsAllAccounts() {
//        List<Accounts> accountsList = this.accountRepo.findAll();
//        List<AccountDto> accountDtoList = new ArrayList<>();
//
//        for(Accounts account: accountsList) {
//            accountDtoList.add(AccountConverter.toAccountDto(account));
//
//        }
//
//        return accountDtoList;

        List<Accounts> accounts = accountRepo.findAll();
         return accounts.stream().map(AccountConverter::toAccountDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) throws AccountNotFoundException {

        Accounts accounts = accountRepo.findById(id).orElseThrow(() -> new AccountNotFoundException("Account does not exit !!!"));

        accountRepo.deleteById(id);
    }

}
