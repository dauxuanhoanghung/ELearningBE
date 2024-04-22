package com.dxhh.elearning.services;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    String uploadImage(MultipartFile file);

}