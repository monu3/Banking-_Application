package org.banking.banking_application.Converter;

import lombok.Builder;
import org.banking.banking_application.Dto.AccountDto;
import org.banking.banking_application.Entity.Accounts;
import org.springframework.stereotype.Component;


@Builder
@Component
public class AccountConverter {


    //AccountDto into account
    public static Accounts toAccounts(AccountDto accountDto){

        //This is constructor base value provided
        Accounts accounts = new Accounts(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getAccountNumber(),
                accountDto.getBalance()
        );
        return accounts;


        //This is getter and setter based Value provided
//        Accounts accounts = new Accounts();
//        accounts.setId(accountDto.getId());
//        accounts.setAccountNumber(accountDto.getAccountNumber());
//        accounts.setBalance(accountDto.getBalance());
//        return accounts;


        //This is builder based value provided
//        return new Accounts
//                .builder()
//                .id(accountDto.getId())
//                .accountHolderName(accountDto.getAccountHolderName())
//                .accountNumber(accountDto.getAccountNumber())
//                .balance(accountDto.getBalance())
//                .build();

    }


    //Accounts into AccountDto
    public static AccountDto toAccountDto(Accounts accounts){
        return new AccountDto(
                accounts.getId(),
                accounts.getAccountHolderName(),
                accounts.getAccountNumber(),
                accounts.getBalance()
        );
    }
}
