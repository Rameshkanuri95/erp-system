package com.erp.service.financial;

import com.erp.exception.ResourceNotFoundException;
import com.erp.model.User;
import com.erp.model.financial.Account;
import com.erp.repository.financial.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Account createAccount(Account account, User creator){
        Account account = Account.builder()
                .accountCode(account.getAccountCode())
                .name(account.getName())
                .type(account.getType())
                .balance(account.getBalance())
                .createdBy(creator)
                .build();

        if (account.getParentAccountId() != null) {
            Account parent = accountRepository.findById(account.getParentAccountId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent account not found"));
            account.setParentAccount(parent);
        }

        return accountRepository.save(account);
    }

    public Account getAccountById(Long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public Account updateAccount(Long id,Account accountDetails,User updater){
        Account account = getAccountById(id);
        account.setName(accountDetails.getName());
        account.setType(accountDetails.getType());
        account.setBalance(accountDetails.getBalance());
        account.setUpdatedBy(updater);
        return accountRepository.save(account);
    }

    @Transactional
    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }

}
