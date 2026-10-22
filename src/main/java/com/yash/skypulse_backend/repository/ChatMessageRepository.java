package com.yash.skypulse_backend.repository;

import com.yash.skypulse_backend.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    
    // Spring Data MongoDB automatically generates this query!
    List<ChatMessage> findByRoomIdOrderByTimestampAsc(String roomId);
}