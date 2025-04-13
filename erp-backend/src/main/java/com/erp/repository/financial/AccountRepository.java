package com.erp.repository.financial;

import com.erp.model.financial.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsByAccountCode(String accountCode);
}
