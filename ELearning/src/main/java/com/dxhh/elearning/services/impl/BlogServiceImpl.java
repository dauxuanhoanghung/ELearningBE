package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.mappers.BlogMapper;
import com.dxhh.elearning.pojos.Blog;
import com.dxhh.elearning.repositories.BlogRepository;
import com.dxhh.elearning.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, BlogMapper blogMapper) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
    }

    // Create a new blog
    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Optional<Blog> getBlogById(Integer id) {
        return blogRepository.findById(id);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Blog updateBlog(Integer id, Blog updatedBlog) {
        Optional<Blog> existingBlogOptional = blogRepository.findById(id);
        if (existingBlogOptional.isPresent()) {
            Blog existingBlog = existingBlogOptional.get();
            existingBlog.setTitle(updatedBlog.getTitle());
            existingBlog.setContent(updatedBlog.getContent());
            existingBlog.setPublishDate(updatedBlog.getPublishDate());
            existingBlog.setAuthor(updatedBlog.getAuthor());
            return blogRepository.save(existingBlog);
        }
        return null;
    }

    public boolean deleteBlog(Integer id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (blogOptional.isPresent()) {
            blogRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
