package com.dxhh.elearning.mappers;

import com.dxhh.elearning.dto.request.NewBlogRequest;
import com.dxhh.elearning.pojos.Blog;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {
    private final ModelMapper mapper;

    @Autowired
    public BlogMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Blog toModel(NewBlogRequest blogRequest) {
        return mapper.map(blogRequest, Blog.class);
    }
}
