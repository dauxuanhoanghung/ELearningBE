package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Long countByCourseId(Integer courseId);
}
