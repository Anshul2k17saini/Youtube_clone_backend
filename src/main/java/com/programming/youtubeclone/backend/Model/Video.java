package com.programming.youtubeclone.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

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
    private AtomicInteger likes= new AtomicInteger(0);
    private AtomicInteger dislike=new AtomicInteger(0);
    private HashSet<String> tags;
    private String videoUrl;
    private VideoStatus videostatus;
    private Set<String> videoHistory;
    private AtomicInteger viewCount=new AtomicInteger(0);;
    private String thumbnailUrl;
    private List<Comment> commentList=new CopyOnWriteArrayList<>();

    public void incrementLikes(){

        likes.incrementAndGet();
    }

    public void decrementLikes(){

        likes.decrementAndGet();
    }
    public void incrementdislike(){

        dislike.incrementAndGet();

    }
    public void decrementdislike(){

        dislike.decrementAndGet();
    }


    public void incrementViewCount() {

        viewCount.incrementAndGet();

    }

    public void addComment(Comment comment) {
        commentList.add(comment);
    }
}
