package com.programming.youtubeclone.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Document(value ="User")
@Data //created the getter setter by lamboc
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private Set<String> subscribedToUsers=ConcurrentHashMap.newKeySet();
    private Set<String> subscribers=ConcurrentHashMap.newKeySet();
    private Set<String> videoHistory=ConcurrentHashMap.newKeySet();;
    private Set<String> likedVideo = ConcurrentHashMap.newKeySet();
    private Set<String>  dislikedVideo = ConcurrentHashMap.newKeySet();
    private String sub;
    public void addlikedvideo(String videoId) {
        likedVideo.add(videoId);
    }
    public void removefromLikedvideo(String videoId) {
      likedVideo.remove(videoId);
    }
    public void removefromDislikedvideo(String videoId) {
        dislikedVideo.remove(videoId);
    }

    public void addToDislikedVideo(String videoId) {
        dislikedVideo.add(videoId);
    }

    public void addToVideoHistory(String videoId) {
        videoHistory.add(videoId);
    }


    public void addToSubscribedToUsers(String userId) {


    }

    public void addToSubscribers(String userid) {
        subscribers.add(userid);
    }

    public void removeFromSubscribedToUsers(String userId) {

        subscribedToUsers.remove(userId);

    }

    public void removeToSubscribers(String userId) {
     subscribers.remove(userId);
    }
}