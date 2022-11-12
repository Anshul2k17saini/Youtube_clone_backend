package com.programming.youtubeclone.backend.controller;


import com.programming.youtubeclone.backend.Dto.CommentDto;
import com.programming.youtubeclone.backend.Model.Comment;
import com.programming.youtubeclone.backend.service.UserRegistrationService;
import com.programming.youtubeclone.backend.service.Userservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRegistrationService userRegistrationService;

    private final Userservice userservice;

    @GetMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public String resgister(Authentication authentication){

        Jwt jwt=(Jwt)authentication.getPrincipal();

       return  userRegistrationService.registerUser(jwt.getTokenValue());

    }

    @PostMapping("subscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean subscribeUser(@PathVariable String userId){
        userservice.subscribeUser(userId);
        return true;
    }

    @PostMapping("unsubscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean unsubscribeUser(@PathVariable String userId){
        userservice.unSubscribeUser(userId);
        return true;
    }

 @GetMapping("/{userId}/history")
 @ResponseStatus(HttpStatus.OK)
    public Set<String> userHistory(@PathVariable String userId) {

        return userservice.userHistory(userId);
 }


}
