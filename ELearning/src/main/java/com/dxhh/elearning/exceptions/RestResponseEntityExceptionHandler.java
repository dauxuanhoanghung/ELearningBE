package com.dxhh.elearning.exceptions;

import com.dxhh.elearning.dto.response.ModelResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<ModelResponse> handleConflict(
            RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ModelResponse.builder()
                .status(400)
                .message(ex.getMessage())
                .data(Arrays.stream(ex.getStackTrace()).limit(10))
                .build());
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<ModelResponse> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return ResponseEntity.badRequest().body(ModelResponse.builder()
                .status(403)
                .message(ex.getMessage())
                .data(Arrays.stream(ex.getStackTrace()).limit(10))
                .build());
    }
}
