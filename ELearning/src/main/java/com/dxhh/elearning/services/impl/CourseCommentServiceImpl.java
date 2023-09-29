package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.pojos.CourseComment;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.CourseCommentRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.CourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class CourseCommentServiceImpl implements CourseCommentService {
    private final CourseCommentRepository courseCommentRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseCommentServiceImpl(CourseCommentRepository courseCommentRepository, UserRepository userRepository) {
        this.courseCommentRepository = courseCommentRepository;
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
    public CourseComment create(CourseComment courseComment) {
        courseComment.setUser(getCurrentUser());
        courseComment.setCreatedDate(LocalDateTime.now());
        return courseCommentRepository.save(courseComment);
    }

    @Override
    public CourseComment getById(Integer commentId) {
        return courseCommentRepository.findById(commentId).orElse(null);
    }

    @Override
    public CourseComment update(Integer commentId, CourseComment updatedComment) {
        CourseComment existingComment = getById(commentId);
        if (existingComment != null) {
            // Update the existing comment with data from updatedComment
            existingComment.setContent(updatedComment.getContent());
            // Update other fields as needed
            return courseCommentRepository.save(existingComment);
        }
        return null; // Comment not found
    }

    @Override
    public boolean deleteById(Integer commentId) {
        CourseComment existingComment = getById(commentId);
        if (existingComment != null) {
            courseCommentRepository.delete(existingComment);
            return true;
        }
        return false; // Comment not found
    }

    @Override
    public List<CourseComment> getByCourseId(Integer courseId, int page) {
        int pageNumber = Math.max(page, 0);

        Pageable pageable = PageRequest.of(pageNumber, 10);
        return courseCommentRepository.findByCourse_Id(courseId, pageable).getContent();
    }
}
