package com.dxhh.elearning.repositories;

import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.pojos.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>, JpaSpecificationExecutor<Transaction> {
    Optional<Transaction> findByUser_EmailAndCourse_Id(String email, Integer courseId);

    Long countByCourse_Id(Integer courseId);

    Long countByUser_Id(Integer userId);

    Page<Transaction> findByUser_Id(Integer userId, Pageable pageable);

    Optional<Transaction> findByUser_IdAndCourse_Id(Integer userId, Integer courseId);

    Boolean existsByUserAndCourse(User user, Course course);
}
