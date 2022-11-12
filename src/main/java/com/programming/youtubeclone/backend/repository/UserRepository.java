package com.programming.youtubeclone.backend.repository;

import com.programming.youtubeclone.backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findBySub(String sub);


}
