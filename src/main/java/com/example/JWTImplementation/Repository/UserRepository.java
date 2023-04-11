package com.example.JWTImplementation.Repository;

import com.example.JWTImplementation.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
    User findByUsername(String username);
}
