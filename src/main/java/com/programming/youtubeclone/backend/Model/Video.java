package com.programming.youtubeclone.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;

@Document(value ="video")
@Data //created the getter setter by lamboc
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    private String id;
    private  String title;
    private String description;
    private String userId;
    private Integer dislike;
    private HashSet<String> tags;
    private String videoUrl;
    private VideoStatus videostatus;
    private Integer viewCount;
    private String thumbnailUrl;
    private ArrayList<Comment> commentList;



}
