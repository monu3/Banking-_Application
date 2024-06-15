package org.banking.banking_application.Services;

import org.banking.banking_application.Dto.AccountDto;

import javax.security.auth.login.AccountNotFoundException;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto findAccountById(Long id) throws AccountNotFoundException;

    AccountDto deposit(Long id,double amount) throws AccountNotFoundException;
}
