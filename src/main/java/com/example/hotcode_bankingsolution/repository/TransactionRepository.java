package com.example.hotcode_bankingsolution.repository;

import com.example.hotcode_bankingsolution.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
