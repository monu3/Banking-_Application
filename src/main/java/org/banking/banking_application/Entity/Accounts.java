package org.banking.banking_application.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id ;

    @Column(name = "account_holder", nullable = false, length = 100)
    public String accountHolderName;

    @Column(name = "account_number", length = 20)
    public String accountNumber;

    @Column(name = "Balance", nullable = false, length = 100)
    public double balance;
}
