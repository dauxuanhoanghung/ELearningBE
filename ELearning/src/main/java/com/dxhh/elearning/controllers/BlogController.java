package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.NewBlogRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.mappers.BlogMapper;
import com.dxhh.elearning.pojos.Blog;
import com.dxhh.elearning.services.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/blogs/")
public class BlogController {

    private final BlogService blogService;
    private final BlogMapper blogMapper;

    public BlogController(BlogService blogService, BlogMapper blogMapper) {
        this.blogService = blogService;
        this.blogMapper = blogMapper;
    }

    @GetMapping
    public ResponseEntity<ModelResponse> retrieveAll(@RequestParam Map<String, String> params){
        ModelResponse res = new ModelResponse();
        res.setStatus(200);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ModelResponse> getById(@PathVariable(name = "id") int id) {
        ModelResponse res = new ModelResponse();
        res.setStatus(200);
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewBlogRequest blogRequest, BindingResult rs) {
        ModelResponse res = new ModelResponse();
        Blog b = blogService.create(blogRequest);
        res.setStatus(200);
        res.setData(b);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping(path = "/{id}/delete")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id) {
        blogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
