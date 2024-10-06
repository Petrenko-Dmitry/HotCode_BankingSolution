package com.example.hotcode_bankingsolution.service;

import com.example.hotcode_bankingsolution.entity.Transaction;
import com.example.hotcode_bankingsolution.exception.NotFoundException;
import com.example.hotcode_bankingsolution.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import static com.example.hotcode_bankingsolution.constants.StringConstants.TRANSACTION_NOT_FOUND_FORMAT;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void saveTransaction(Transaction transaction) {
        this.transactionRepository.save(transaction);
    }

    public Transaction getTransaction(Long id) {
        return this.transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(TRANSACTION_NOT_FOUND_FORMAT, id)));
    }

    public void deleteTransaction(Long id) {
        var transaction = this.getTransaction(id);
        this.transactionRepository.delete(transaction);
    }
}
