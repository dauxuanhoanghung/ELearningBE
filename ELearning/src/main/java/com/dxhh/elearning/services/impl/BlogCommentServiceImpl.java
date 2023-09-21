package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.BlogCommentRequest;
import com.dxhh.elearning.pojos.Blog;
import com.dxhh.elearning.pojos.BlogComment;
import com.dxhh.elearning.pojos.User;
import com.dxhh.elearning.repositories.BlogCommentRepository;
import com.dxhh.elearning.repositories.BlogRepository;
import com.dxhh.elearning.repositories.UserRepository;
import com.dxhh.elearning.services.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlogCommentServiceImpl implements BlogCommentService {

    private final BlogCommentRepository blogCommentRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Autowired
    public BlogCommentServiceImpl(BlogCommentRepository blogCommentRepository, BlogRepository blogRepository, UserRepository userRepository) {
        this.blogCommentRepository = blogCommentRepository;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername()).get(0);
    }

    @Override
    public BlogComment create(BlogCommentRequest commentRequest) {
        Optional<Blog> blogOptional = blogRepository.findById(commentRequest.getBlog().getId());/**/
        User user = getCurrentUser();

        if (blogOptional.isPresent() && user != null) {
            BlogComment comment = new BlogComment();
            comment.setContent(commentRequest.getContent());
            comment.setBlog(blogOptional.get());
            comment.setUser(user);
            return blogCommentRepository.save(comment);
        } else {
            throw new IllegalArgumentException("Blog or User not found with the provided IDs.");
        }
    }

    @Override
    public Optional<BlogComment> getById(Integer id) {
        return blogCommentRepository.findById(id);
    }

    @Override
    public List<BlogComment> getAll() {
        return blogCommentRepository.findAll();
    }

    @Override
    public BlogComment update(Integer id, BlogCommentRequest updatedCommentRequest) {
        Optional<BlogComment> existingCommentOptional = blogCommentRepository.findById(id);
        if (existingCommentOptional.isPresent()) {
            BlogComment existingComment = existingCommentOptional.get();
            existingComment.setContent(updatedCommentRequest.getContent());
            return blogCommentRepository.save(existingComment);
        } else {
            throw new IllegalArgumentException("BlogComment not found with the provided ID.");
        }
    }

    @Override
    public boolean delete(Integer id) {
        Optional<BlogComment> commentOptional = blogCommentRepository.findById(id);
        if (commentOptional.isPresent()) {
            blogCommentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
