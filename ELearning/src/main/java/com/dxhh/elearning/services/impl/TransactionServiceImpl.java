package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewTransactionRequest;
import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.repositories.TransactionRepository;
import com.dxhh.elearning.services.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction create(NewTransactionRequest newTransactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setAmount(newTransactionRequest.getAmount());
        transaction.setCourse(newTransactionRequest.getCourse());

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction update(Integer transactionId, NewTransactionRequest updatedTransaction) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));

        transaction.setAmount(updatedTransaction.getAmount());
        transaction.setCourse(updatedTransaction.getCourse());

        return transactionRepository.save(transaction);
    }

    @Override
    public void delete(Integer transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public Transaction getById(Integer transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
}
