package com.programming.youtubeclone.backend.repository;

import com.programming.youtubeclone.backend.Model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Videorepositry extends MongoRepository<Video,String> {


}
