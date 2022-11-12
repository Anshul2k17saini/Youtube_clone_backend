package com.programming.youtubeclone.backend.Dto;

import com.programming.youtubeclone.backend.Model.Comment;
import com.programming.youtubeclone.backend.Model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videodto {

    private String id;
    private  String title;
    private String description;
    private HashSet<String> tags;
    private String videoUrl;
    private VideoStatus videostatus;
    private String thumbnailUrl;
    private Integer LikeCount;
    private Integer dislikeCount;
    private Integer viewCount;

}
