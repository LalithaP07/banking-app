package com.itap.bankingapp.service;

import com.itap.bankingapp.model.Account;
import com.itap.bankingapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // ================================
    // CREATE — Add new account
    // ================================
    public Account createAccount(Account account) {
        // Check if email already exists
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new RuntimeException("Account with this email already exists!");
        }
        return accountRepository.save(account);
    }

    // ================================
    // READ ALL — Get all accounts
    // ================================
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // ================================
    // READ ONE — Get account by ID
    // ================================
    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RuntimeException("Account not found with ID: " + id);
        }
    }

    // ================================
    // READ ONE — Get by account number
    // ================================
    public Account getAccountByNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new RuntimeException("Account not found: " + accountNumber);
        }
    }

    // ================================
    // UPDATE — Update account details
    // ================================
    public Account updateAccount(Long id, Account updatedAccount) {
        // First find the existing account
        Account existingAccount = getAccountById(id);

        // Update only the fields that are provided
        if (updatedAccount.getHolderName() != null) {
            existingAccount.setHolderName(updatedAccount.getHolderName());
        }
        if (updatedAccount.getEmail() != null) {
            existingAccount.setEmail(updatedAccount.getEmail());
        }
        if (updatedAccount.getPhone() != null) {
            existingAccount.setPhone(updatedAccount.getPhone());
        }

        return accountRepository.save(existingAccount);
    }

    // ================================
    // DELETE — Close account
    // ================================
    public String closeAccount(Long id) {
        Account account = getAccountById(id);
        account.setStatus("CLOSED");
        accountRepository.save(account);
        return "Account " + account.getAccountNumber() + " has been closed.";
    }

    // ================================
    // SEARCH — Find by name
    // ================================
    public List<Account> getAllActiveAccounts() {
        return accountRepository.findByStatus("ACTIVE");
    }
}