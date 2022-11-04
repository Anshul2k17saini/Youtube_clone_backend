package com.programming.youtubeclone.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3Service implements FileService{

    @Override
    public String uploadFile(MultipartFile file){

        //upload file to aws s3
        //file.getOriginalFilename()
        return "";
    }

}
