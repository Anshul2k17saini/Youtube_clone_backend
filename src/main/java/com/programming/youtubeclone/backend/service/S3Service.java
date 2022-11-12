package com.programming.youtubeclone.backend.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class S3Service implements FileService{

    private final AmazonS3Client awss3client;

    @Override
    public String uploadFile(MultipartFile file){

        //upload file to aws s3
        var filenameExtension=StringUtils.getFilenameExtension(file.getOriginalFilename());

        var key=UUID.randomUUID().toString()+ filenameExtension;

        var metadata= new ObjectMetadata();

        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            awss3client.putObject("anshuls3bucket1302", key, file.getInputStream(), metadata);
        } catch(IOException ioException)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An Exception accured while uploading the file");
        }

        awss3client.setObjectAcl("anshuls3bucket1302", key, CannedAccessControlList.PublicRead);//so we can read without any authentication

        return awss3client.getResourceUrl("anshuls3bucket1302",key);

    }

}
