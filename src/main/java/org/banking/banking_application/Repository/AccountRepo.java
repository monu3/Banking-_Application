package org.banking.banking_application.Repository;

import org.banking.banking_application.Entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Accounts,Long> {

}
