package com.dxhh.elearning.controllers;

import com.dxhh.elearning.dto.request.BlogCommentRequest;
import com.dxhh.elearning.dto.response.ModelResponse;
import com.dxhh.elearning.pojos.BlogComment;
import com.dxhh.elearning.services.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/blog-comments/", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogCommentController {
    private final BlogCommentService blogCommentService;

    @Autowired
    public BlogCommentController(BlogCommentService blogCommentService) {
        this.blogCommentService = blogCommentService;
    }


    @PostMapping
    public ResponseEntity<ModelResponse> create(@RequestBody BlogCommentRequest blogComment) {
        BlogComment savedBlogComment = blogCommentService.create(blogComment);
        ModelResponse response = new ModelResponse();
        response.setStatus(201);
        response.setData(savedBlogComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{blogId}")
    public ResponseEntity<ModelResponse> getCommentByBlog(@PathVariable("blogId") Integer blogId,
                                                          @RequestParam Map<String, String> params) {
        List<BlogComment> comments = blogCommentService.getAll();

        ModelResponse response = new ModelResponse();
        response.setStatus(200);
        response.setData(comments);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ModelResponse> retrieveAll(@RequestParam Map<String, String> params){
        ModelResponse res = new ModelResponse();
        res.setStatus(200);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<ModelResponse> updateCourseComment( @PathVariable Integer commentId,
                                                              @RequestBody BlogComment updatedComment) {
        BlogComment updated = blogCommentService.update(updatedComment.getId(), null);
        ModelResponse response = new ModelResponse();
        if (updated != null) {
            response.setStatus(200);
            response.setData(updated);
            return ResponseEntity.ok(response);
        } else {
            response.setStatus(404);
            response.setMessage("Blog comment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ModelResponse> deleteLectureComment(@PathVariable("id") Integer id) {
        boolean deleted = blogCommentService.deleteById(id);
        ModelResponse response = new ModelResponse();
        if (deleted) {
            response.setStatus(204);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } else {
            response.setStatus(404);
            response.setMessage("Blog comment not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
