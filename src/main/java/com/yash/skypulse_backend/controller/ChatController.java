package com.yash.skypulse_backend.controller;

import com.yash.skypulse_backend.model.ChatMessage;
import com.yash.skypulse_backend.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class ChatController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    // GET /api/v1/rooms/{roomId}/messages -> Fetch persistent history from MongoDB
    @GetMapping("/{roomId}/messages")
    public List<ChatMessage> getRoomHistory(@PathVariable String roomId) {
        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId);
    }
}