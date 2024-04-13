package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewTransactionRequest;
import com.dxhh.elearning.pojos.Course;
import com.dxhh.elearning.pojos.Transaction;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.CourseRepository;
import com.dxhh.elearning.repositories.TransactionRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CurrentUserService;
import com.dxhh.elearning.services.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl extends CurrentUserService implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  CourseRepository courseRepository,
                                  UserRepository userRepository) {
        super(userRepository);
        this.transactionRepository = transactionRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Transaction create(NewTransactionRequest newTransactionRequest) {
        Optional<Course> optionalCourse = courseRepository.findById(newTransactionRequest.getCourse().getId());
        Course course = optionalCourse.orElse(null);
        User currentUser = this.getCurrentUser();
        User receiver = currentUser;

        if (newTransactionRequest.getUsername() != null && !newTransactionRequest.getUsername().isBlank()) {
            User u = userRepository.findByUsername(newTransactionRequest.getUsername()).get(0);
            if (u == null) {
                return null;
            }
            receiver = u;
        }

        if (course == null) {
            return null;
        }

        Transaction transaction = Transaction.builder()
                .amount(newTransactionRequest.getAmount())
                .originalAmount(BigDecimal.valueOf(course.getPrice()))
                .course(course)
                .payer(currentUser)
                .user(receiver)
                .createdDate(LocalDateTime.now())
                .build();
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getByCurrentUserAndCourse(Integer courseId) {
        User user = getCurrentUser();
        assert user != null;
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
