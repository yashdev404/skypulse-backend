package com.yash.skypulse_backend.repository;

import com.yash.skypulse_backend.model.FeedItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// This interface gives us all the CRUD operations for MongoDB automatically!
@Repository
public interface FeedRepository extends MongoRepository<FeedItem, String> {
}