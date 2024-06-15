package org.banking.banking_application.Dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private String accountNumber;
    private double balance;
}
