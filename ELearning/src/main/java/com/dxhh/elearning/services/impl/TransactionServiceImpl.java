package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewTransactionRequest;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.CourseRepository;
import com.dxhh.elearning.repositories.TransactionRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        List<User> users = this.userRepository.findByUsername(authentication.getName());
        if (users.isEmpty())
            return null;
        return users.get(0);
    }

    @Override
    public Transaction create(NewTransactionRequest newTransactionRequest) {
        Course course = courseRepository.findById(newTransactionRequest.getCourse().getId()).get();
        Transaction transaction = new Transaction();
        if (course.getPrice() != 0) {

        } else
            transaction.setAmount(BigDecimal.valueOf(course.getPrice()));
        transaction.setCourse(newTransactionRequest.getCourse());
        transaction.setUser(getCurrentUser());
        transaction.setCreatedDate(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getByCurrentUserAndCourse(Integer courseId) {
        User user = getCurrentUser();
        Optional<Transaction> existingRegister = transactionRepository.findByUser_IdAndCourse_Id(user.getId(), courseId);
        return existingRegister.orElse(null);
    }

    @Override
    public Transaction update(Integer transactionId, NewTransactionRequest updatedTransaction) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
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
