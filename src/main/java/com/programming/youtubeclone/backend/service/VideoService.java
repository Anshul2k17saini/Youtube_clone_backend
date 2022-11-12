package com.programming.youtubeclone.backend.service;

import com.programming.youtubeclone.backend.Dto.CommentDto;
import com.programming.youtubeclone.backend.Dto.UploadVideoResponse;
import com.programming.youtubeclone.backend.Dto.Videodto;
import com.programming.youtubeclone.backend.Model.Comment;
import com.programming.youtubeclone.backend.Model.Video;
import com.programming.youtubeclone.backend.repository.Videorepositry;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoService {
  private final S3Service s3Service;
  private final Videorepositry videorepositry;

   private final Userservice userservice;


    public UploadVideoResponse uploadVideo(MultipartFile multipartFile)
    {
        //upload file to aws s3
        //save video to database
        String videoUrl= s3Service.uploadFile(multipartFile);
       var video=new Video();
       video.setVideoUrl(videoUrl);
       //saved object in mongoDb
       var savedVideo= videorepositry.save(video);
       return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());

    }


    public Videodto editvideo(Videodto videodto)
    {
        //Find video by id
       Video saveVideo= getVideoById(videodto.getId());//Map the videoDto fields to video
        saveVideo.setTitle(videodto.getTitle());
        saveVideo.setDescription(videodto.getDescription());
        saveVideo.setTags(videodto.getTags());
        saveVideo.setThumbnailUrl(videodto.getThumbnailUrl());
        saveVideo.setVideostatus(videodto.getVideostatus() );

        //save video to database
          videorepositry.save(saveVideo);
          return videodto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {

        Video saveVideo= getVideoById(videoId);//Map the videoDto fields to video
        String thumbnailurl= s3Service.uploadFile(file);//get the url of saved

        saveVideo.setThumbnailUrl(thumbnailurl);
        videorepositry.save(saveVideo);//saved in database
         return thumbnailurl;
    }

    public Video getVideoById(String videoid)
    {
       return videorepositry.findById(videoid).orElseThrow(()->new IllegalArgumentException("cannot find the id"));

    }


    public Videodto getVideodetails(String videoId) {
          Video saveVideo = getVideoById((videoId)) ;


          increasedVideoCount(saveVideo);
        userservice.addVideoToHistory(videoId);

          Videodto videodto=new Videodto();
          videodto.setVideoUrl(saveVideo.getVideoUrl());
          videodto.setThumbnailUrl(saveVideo.getThumbnailUrl());
          videodto.setId(saveVideo.getId());
          videodto.setTitle(saveVideo.getTitle());
          videodto.setDescription(saveVideo.getDescription());
          videodto.setTags(saveVideo.getTags());
          videodto.setVideostatus(saveVideo.getVideostatus());
          videodto.setLikeCount(saveVideo.getLikes().get());
          videodto.setDislikeCount(saveVideo.getDislike().get());
          videodto.setViewCount(saveVideo.getViewCount().get());

          return videodto;

    }

    private void increasedVideoCount(Video saveVideo) {

      saveVideo.incrementViewCount();
       videorepositry.save(saveVideo);

    }

    public Videodto likeVideo(String videoId) {

        Video VideoById= getVideoById(videoId);

       // Increment like count
        // if user already liked the video ,then decement like count
       //like 0 dislike 0;
        //like 1 dislike 0;
        //like 0 dislike 0;
        //like 0 dislike 1;

        if(userservice.ifLikedVideo(videoId))
        {
            VideoById.decrementdislike();
            userservice.removeFromLikedVideos(videoId);

        }else if(userservice.ifDisLikedVideo(videoId))
        {
            VideoById.decrementdislike();
            userservice.removefromdislikedVideo(videoId);
            VideoById.incrementLikes();
            userservice.addToLikedVideos(videoId);
        }else {
            VideoById.incrementLikes();
            userservice.addToLikedVideos(videoId);
        }

        videorepositry.save(VideoById);
        Videodto videodto=new Videodto();
        videodto.setVideoUrl(VideoById.getVideoUrl());
        videodto.setThumbnailUrl(VideoById.getThumbnailUrl());
        videodto.setId(VideoById.getId());
        videodto.setTitle(VideoById.getTitle());
        videodto.setDescription(VideoById.getDescription());
        videodto.setTags(VideoById.getTags());
        videodto.setVideostatus(VideoById.getVideostatus());
        videodto.setLikeCount(VideoById.getDislike().get());
        videodto.setDislikeCount(VideoById.getDislike().get());
        videodto.setDislikeCount(VideoById.getDislike().get());
        videodto.setViewCount(VideoById.getViewCount().get());

        return videodto;
    }
    private Videodto mapToVideoDto(Video videoById) {
        Videodto videoDto = new Videodto();
        videoDto.setVideoUrl(videoById.getVideoUrl());
        videoDto.setThumbnailUrl(videoById.getThumbnailUrl());
        videoDto.setId(videoById.getId());
        videoDto.setTitle(videoById.getTitle());
        videoDto.setDescription(videoById.getDescription());
        videoDto.setTags(videoById.getTags());
        videoDto.setVideostatus(videoById.getVideostatus());
        videoDto.setLikeCount(videoById.getLikes().get());
        videoDto.setDislikeCount(videoById.getDislike().get());
        videoDto.setViewCount(videoById.getViewCount().get());
        return videoDto;
    }

    public Videodto dislikeVideo(String videoId) {

        Video VideoById= getVideoById(videoId);

        // Increment like count
        // if user already liked the video ,then decement like count
        //like 0 dislike 0;
        //like 1 dislike 0;
        //like 0 dislike 0;
        //like 0 dislike 1;

        if(userservice.ifLikedVideo(videoId))
        {
            VideoById.decrementdislike();
            userservice.removefromdislikedVideo(videoId);

        }else if(userservice.ifDisLikedVideo(videoId))
        {
            VideoById.decrementLikes();
            userservice.removefromdislikedVideo(videoId);
            VideoById.incrementdislike();
            userservice.addTodisLikedVideos(videoId);
        }else {
            VideoById.incrementdislike();
            userservice.addTodisLikedVideos(videoId);
        }

        videorepositry.save(VideoById);
        Videodto videodto=new Videodto();
        videodto.setVideoUrl(VideoById.getVideoUrl());
        videodto.setThumbnailUrl(VideoById.getThumbnailUrl());
        videodto.setId(VideoById.getId());
        videodto.setTitle(VideoById.getTitle());
        videodto.setDescription(VideoById.getDescription());
        videodto.setTags(VideoById.getTags());
        videodto.setVideostatus(VideoById.getVideostatus());
        videodto.setLikeCount(VideoById.getDislike().get());
        videodto.setDislikeCount(VideoById.getDislike().get());
        videodto.setDislikeCount(VideoById.getDislike().get());
        videodto.setViewCount(VideoById.getViewCount().get());

        return videodto;

    }

    public void addComment(String videoId, CommentDto commentDto) {

        Video video=getVideoById(videoId);
        Comment comment=new Comment();
        comment.setText(commentDto.getCommentText());
        comment.setAuthorId(commentDto.getAuthorId());
        video.addComment(comment);

        videorepositry.save(video);

    }

    public List<CommentDto> getAllComments(String videoId) {
        Video video=getVideoById(videoId);
        List<Comment> commentList= video.getCommentList();
        return commentList.stream().map(this::mapToCommentDto).collect(Collectors.toList());

    }

    private CommentDto mapToCommentDto(Comment comment) {

        CommentDto commentDto=new CommentDto();
        commentDto.setCommentText(comment.getText());
        commentDto.setAuthorId(comment.getAuthorId());
        return commentDto;
    }

    public List<Videodto> getAllVideos() {
        return videorepositry.findAll().stream().map(this::mapToVideoDto).collect(Collectors.toList());
    }
}
