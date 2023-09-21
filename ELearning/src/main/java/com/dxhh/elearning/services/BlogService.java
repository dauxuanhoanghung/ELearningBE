package com.dxhh.elearning.services;

import com.dxhh.elearning.pojos.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    List<Blog> getAllBlogs();
    Blog createBlog(Blog blog);
    Optional<Blog> getBlogById(Integer id);
    Blog updateBlog(Integer id, Blog updatedBlog);
    boolean deleteBlog(Integer id);
}
