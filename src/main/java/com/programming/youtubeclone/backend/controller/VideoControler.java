package com.programming.youtubeclone.backend.controller;

import com.programming.youtubeclone.backend.Dto.CommentDto;
import com.programming.youtubeclone.backend.Dto.UploadVideoResponse;
import com.programming.youtubeclone.backend.Dto.Videodto;
import com.programming.youtubeclone.backend.Model.Video;
import com.programming.youtubeclone.backend.repository.Videorepositry;
import com.programming.youtubeclone.backend.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoControler {

    private final VideoService videoService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//to get a http status after success
    public UploadVideoResponse   uploadVideo(@RequestParam("file")MultipartFile file)
    {
        return videoService.uploadVideo(file);
    }


    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)//to get a http status after success
    public String uploadthumbnail(@RequestParam("file")MultipartFile file,@RequestParam("videoId")String videoId)
    {
         return videoService.uploadThumbnail(file,videoId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public  Videodto editvideoMetsdata(@RequestBody Videodto videoDto){

        return videoService.editvideo(videoDto);
    }

    @GetMapping("/videoId")
    @ResponseStatus(HttpStatus.OK)
    public Videodto getvideoDetails(@PathVariable String videoId){
        return videoService.getVideodetails(videoId);
    }

    @PostMapping("/{videoId}/like")
    @ResponseStatus(HttpStatus.OK)
    public Videodto likeVideo(@PathVariable String videoId)
    {
        return videoService.likeVideo(videoId);
    }

    @PostMapping("/{videoId}/dislike")
    @ResponseStatus(HttpStatus.OK)
    public Videodto dislikeVideo(@PathVariable String videoId)
    {
        return videoService.dislikeVideo(videoId);
    }

    @PostMapping("/{videoId}/comment")
    public void addComment(@PathVariable String videoId, @RequestBody CommentDto commentDto)
    {
        videoService.addComment(videoId, commentDto);
    }

    @GetMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComment(@PathVariable String videoId){
        return videoService.getAllComments(videoId);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Videodto> getAllVideo(){
        return videoService.getAllVideos();
    }

}
