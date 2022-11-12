package com.programming.youtubeclone.backend.service;


import com.programming.youtubeclone.backend.Model.User;
import com.programming.youtubeclone.backend.Model.Video;
import com.programming.youtubeclone.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class Userservice {

    private final UserRepository userRepository;

    public User getCurrentUser(){

        String sub =((Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getClaim("sub");
        return userRepository.findBySub(sub).orElseThrow(()->new IllegalArgumentException("Cannot find user with sub-"+ sub));

    }

    public void addToLikedVideos(String videoId)
    {
        User currentUser =getCurrentUser();
        currentUser.addlikedvideo(videoId);
        userRepository.save(currentUser);
    }

    public boolean ifLikedVideo(String videoId)
    {
        return getCurrentUser().getLikedVideo().stream().anyMatch(LikedVideo ->LikedVideo.equals(videoId));

    }

    public boolean ifDisLikedVideo(String videoId)
    {
        return getCurrentUser().getDislikedVideo().stream().anyMatch(LikedVideo ->LikedVideo.equals(videoId));
    }


    public void removeFromLikedVideos(String videoId) {

        User currentUser =getCurrentUser();
        currentUser.removefromLikedvideo(videoId);
        userRepository.save(currentUser);

    }

    public void removefromdislikedVideo(String videoId) {

        User currentUser =getCurrentUser();
        currentUser.removefromDislikedvideo(videoId);
        userRepository.save(currentUser);

    }

    public void addTodisLikedVideos(String videoId) {

        User currentUser =getCurrentUser();
        currentUser.addToDislikedVideo(videoId);
        userRepository.save(currentUser);


    }

    public void addVideoToHistory(String videoId) {

       User currentUser =getCurrentUser();
       currentUser.addToVideoHistory(videoId);
       userRepository.save(currentUser);
    }

    public void subscribeUser(String userId) {
     //Retrieve the current user and add the userId to the subscribed to users set
     //Retrieve the target user and the current user to the subscribers list
        User currentUser = getCurrentUser();
        currentUser.addToSubscribedToUsers(userId);
        User user= userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Cannot find userId"+ userId));
        user.addToSubscribers(currentUser.getId());

        userRepository.save(currentUser);
        userRepository.save(user);

    }

    public void unSubscribeUser(String userId) {
        //Retrieve the current user and add the userId to the subscribed to users set
        //Retrieve the target user and the current user to the subscribers list
        User currentUser = getCurrentUser();
        currentUser.removeFromSubscribedToUsers(userId);
        User user= userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Cannot find userId"+ userId));
        user.removeToSubscribers(currentUser.getId());

        userRepository.save(currentUser);
        userRepository.save(user);
    }


    public Set<String> userHistory(String userId) {

        User user = getUserById(userId);
        return user.getVideoHistory();

    }
    private User getUserById(String userId)
    {
        return userRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("Cannot find userId"+ userId));
    }

}
