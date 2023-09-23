package com.dxhh.elearning.services;

import com.dxhh.elearning.dto.request.NewBlogRequest;
import com.dxhh.elearning.pojos.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> getAllBlogs();
    Blog create(NewBlogRequest blog);
    Optional<Blog> getById(Integer id);
    Blog update(Integer id, Blog updatedBlog);
    boolean deleteById(Integer id);
}
