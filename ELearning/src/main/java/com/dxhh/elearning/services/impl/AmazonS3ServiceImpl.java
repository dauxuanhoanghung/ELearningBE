package com.dxhh.elearning.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.dxhh.elearning.services.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;

@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    private final AmazonS3 amazonS3;
    @Autowired
    public AmazonS3ServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String uploadFile(File file) {
        String path = String.format("%s%s",
                file.getName().replace(' ', '_'), LocalDateTime.now().toString());

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, path, file);
        PutObjectResult result = amazonS3.putObject(putObjectRequest);

        URL s3Url = amazonS3.getUrl(bucketName, path);
        file.delete();

        return s3Url.toString();
    }
}
