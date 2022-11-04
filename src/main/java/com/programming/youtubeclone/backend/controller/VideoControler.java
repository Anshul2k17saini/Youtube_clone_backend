package com.programming.youtubeclone.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
public class VideoControler {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//to get a http status after success
    public void uploadVideo(@RequestParam("file")MultipartFile file)
    {

    }

}
