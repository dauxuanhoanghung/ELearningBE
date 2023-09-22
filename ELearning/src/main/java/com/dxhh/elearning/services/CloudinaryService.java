package com.dxhh.elearning.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) {
        try {
            Map uploadResult = this.cloudinary.uploader()
                    .upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto",
                            "folder", "DoAn"));
//            ObjectUtils.asMap(
//                         // Specify the folder here
//                        "width", width, // Set the width for resizing
//                        "height", height // Set the height for resizing
//            )
            return (String) uploadResult.get("secure_url");
        } catch (IOException ex) {
            Logger.getLogger(CloudinaryService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}