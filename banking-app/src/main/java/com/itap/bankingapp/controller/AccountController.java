package com.itap.bankingapp.controller;

import com.itap.bankingapp.model.Account;
import com.itap.bankingapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // ================================
    // CREATE
    // POST /api/v1/accounts
    // ================================
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account created = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ================================
    // READ ALL
    // GET /api/v1/accounts
    // ================================
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // ================================
    // READ ONE BY ID
    // GET /api/v1/accounts/1
    // ================================
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    // ================================
    // READ ONE BY ACCOUNT NUMBER
    // GET /api/v1/accounts/number/BOFA123456
    // ================================
    @GetMapping("/number/{accountNumber}")
    public ResponseEntity<Account> getByAccountNumber(@PathVariable String accountNumber) {
        Account account = accountService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    // ================================
    // UPDATE
    // PUT /api/v1/accounts/1
    // ================================
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(
            @PathVariable Long id,
            @RequestBody Account account) {
        Account updated = accountService.updateAccount(id, account);
        return ResponseEntity.ok(updated);
    }

    // ================================
    // DELETE (CLOSE)
    // DELETE /api/v1/accounts/1
    // ================================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> closeAccount(@PathVariable Long id) {
        String message = accountService.closeAccount(id);
        return ResponseEntity.ok(message);
    }

    // ================================
    // GET ALL ACTIVE ACCOUNTS
    // GET /api/v1/accounts/active
    // ================================
    @GetMapping("/active")
    public ResponseEntity<List<Account>> getActiveAccounts() {
        List<Account> accounts = accountService.getAllActiveAccounts();
        return ResponseEntity.ok(accounts);
    }
}