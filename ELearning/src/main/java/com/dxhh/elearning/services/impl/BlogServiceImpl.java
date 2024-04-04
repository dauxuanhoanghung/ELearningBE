package com.dxhh.elearning.services.impl;

import com.dxhh.elearning.dto.request.NewBlogRequest;
import com.dxhh.elearning.mappers.BlogMapper;
import com.dxhh.elearning.pojos.Blog;
import com.dxhh.elearning.repositories.BlogRepository;
import com.dxhh.elearning.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    @Override
    public Blog create(NewBlogRequest blog) {
        return blogRepository.save(blogMapper.toModel(blog));
    }

    @Override
    @Cacheable(cacheNames = "blog.id", key = "id")
    public Optional<Blog> getById(Integer id) {
        return blogRepository.findById(id);
    }

    @Override
    @Cacheable(cacheNames = "blogs.list")
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog update(Integer id, Blog updatedBlog) {
        Optional<Blog> existingBlogOptional = blogRepository.findById(id);
        if (existingBlogOptional.isPresent()) {
            Blog existingBlog = existingBlogOptional.get();
            existingBlog.setTitle(updatedBlog.getTitle());
            existingBlog.setContent(updatedBlog.getContent());
            existingBlog.setCreatedDate(updatedBlog.getCreatedDate());
            existingBlog.setAuthor(updatedBlog.getAuthor());
            return blogRepository.save(existingBlog);
        }
        return null;
    }

    @Override
    public boolean deleteById(Integer id) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (blogOptional.isPresent()) {
            blogRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
