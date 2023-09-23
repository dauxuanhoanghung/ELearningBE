package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.BlogCommentRequest;
import com.dxhh.elearning.pojos.BlogComment;

import java.util.List;
import java.util.Optional;

public interface BlogCommentService {
    BlogComment create(BlogCommentRequest commentRequest);
    Optional<BlogComment> getById(Integer id);
    List<BlogComment> getAll();
    BlogComment update(Integer id, BlogCommentRequest updatedCommentRequest);
    boolean deleteById(Integer id);
}
