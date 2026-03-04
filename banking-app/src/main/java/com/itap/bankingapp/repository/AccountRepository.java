package com.itap.bankingapp.repository;

import com.itap.bankingapp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Find account by account number
    Optional<Account> findByAccountNumber(String accountNumber);

    // Find account by email
    Optional<Account> findByEmail(String email);

    // Find all accounts by status (ACTIVE, CLOSED etc)
    List<Account> findByStatus(String status);

    // Check if email already exists
    boolean existsByEmail(String email);
}