package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.NewTransactionRequest;
import com.dxhh.elearning.pojos.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    Transaction create(NewTransactionRequest newTransactionRequest);

    Transaction getByCurrentUserAndCourse(Integer courseId);

    Transaction update(Integer transactionId, NewTransactionRequest updatedTransaction);

    void delete(Integer transactionId);

    Transaction getById(Integer transactionId);

    List<Transaction> getAll(Map<String, String> params);
}
