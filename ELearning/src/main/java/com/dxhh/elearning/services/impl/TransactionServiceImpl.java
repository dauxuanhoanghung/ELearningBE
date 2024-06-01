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
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl extends CurrentUserService implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CourseRepository courseRepository;
    private final Environment env;


    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  CourseRepository courseRepository,
                                  UserRepository userRepository,
                                  Environment env) {
        super(userRepository);
        this.transactionRepository = transactionRepository;
        this.courseRepository = courseRepository;
        this.env = env;
    }

    @Override
    public Transaction create(NewTransactionRequest newTransactionRequest) {
        Optional<Course> optionalCourse = courseRepository.findById(newTransactionRequest.getCourse().getId());
        Course course = optionalCourse.orElse(null);
        User currentUser = this.getCurrentUser();
        User receiver = currentUser;

        if (newTransactionRequest.getPayeeEmail() != null && !newTransactionRequest.getPayeeEmail().isBlank()) {
            List<User> users = userRepository.findByEmail(newTransactionRequest.getPayeeEmail());
            if (users.isEmpty()) {
                return null;
            }
            receiver = users.get(0);
        }

        if (course == null) {
            return null;
        }

        Transaction transaction = Transaction.builder()
                .amount(newTransactionRequest.getAmount())
                .originalAmount(BigDecimal.valueOf(course.getPrice()))
                .course(course)
                .code(newTransactionRequest.getCode())
                .method(newTransactionRequest.getMethod())
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
    public Transaction getByUserEmailAndCourseId(Map<String, String> request) {
        Integer courseId = Integer.parseInt(request.get("courseId"));
        String userEmail = request.get("email");
        List<User> users = userRepository.findByEmail(userEmail);
        if (users.isEmpty()) {
            return null;
        }
        User user = users.get(0);
        Transaction transaction = transactionRepository
                .findByUser_EmailAndCourse_Id(userEmail, courseId)
                .orElse(new Transaction(0));
        return transaction;
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
    public List<Transaction> getAll(Map<String, String> params) {
        int page = Integer.parseInt(params.get("page"));
        int pageNumber = Math.max(page, 0);
        int size = env.getProperty("SIZE", Integer.class, 8);
        if (params.containsKey("pageSize")) {
            size = Integer.parseInt(params.get("pageSize"));
        }

        Pageable pageable = PageRequest.of(pageNumber, size);

        if (!params.containsKey("admin")) {
            User user = getCurrentUser();
            return transactionRepository.findByUser_Id(user.getId(), pageable).getContent();
        }

        return transactionRepository.findAll(pageable).getContent();
    }

    @Override
    public Integer count(Map<String, String> params) {
        if (!params.containsKey("admin")) {
            User user = getCurrentUser();
            return Math.toIntExact(transactionRepository.countByUser_Id(user.getId()));
        }
        return Math.toIntExact(transactionRepository.count());
    }
}
