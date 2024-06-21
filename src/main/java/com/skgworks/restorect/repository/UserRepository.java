package com.skgworks.restorect.repository;

import com.skgworks.restorect.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query("{ 'username' : ?0 }")
    Optional<User> findByUsername(@NonNull String username);

}