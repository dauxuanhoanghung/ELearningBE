package com.dxhh.elearning.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class Utils {
    private final Environment env;

    @Autowired
    public Utils(Environment env) {
        this.env = env;
    }

    public Map<String, String> getAllErrorValidation(BindingResult result) {
        try {
            Map<String, String> errors = new HashMap<>();
            result.getAllErrors().forEach((error) -> {

                String fieldName = ((FieldError) error).getField();
                String message = "";
                if (error.getDefaultMessage() != null) {
                    message = error.getDefaultMessage();
                } else {
                    message = env.getProperty(error.getCode());
                }
                errors.put(fieldName, message);
            });
            return errors;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    public boolean isNotEmptyFile(MultipartFile file) {
        return file != null && !file.isEmpty() && file.getSize() > 0;
    }

    public boolean isVideoFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return false;
        }

        String fileName = file.getOriginalFilename();
        if (fileName != null) {
            fileName = fileName.toLowerCase();
            return fileName.endsWith(".mp4") || fileName.endsWith(".avi")
                    || fileName.endsWith(".mov") || fileName.endsWith("webm")
                    || fileName.endsWith(".mkv") || fileName.endsWith(".mk3d")
                    || fileName.endsWith(".qt");
        }

        return false;
    }

    public File multipartToFile(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
            return file;
        } catch (Exception err) {
            System.out.println("Video error");
        }
        return null;
    }
}
