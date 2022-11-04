package com.programming.youtubeclone.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;

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
    private HashSet<String> subscribedToUsers;
    private HashSet<String> subscribers;
    private ArrayList<String> videoHistory;
    private HashSet<String>  likedVideo;
    private HashSet<String>  dislikedVideo;


}
