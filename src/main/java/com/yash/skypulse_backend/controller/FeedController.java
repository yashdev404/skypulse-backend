package com.yash.skypulse_backend.controller;

import com.yash.skypulse_backend.model.FeedItem;
import com.yash.skypulse_backend.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feed")
public class FeedController {

    // Spring automatically injects the MongoDB repository here
    @Autowired
    private FeedRepository feedRepository;

    // GET /api/v1/feed -> Returns all items from MongoDB
    @GetMapping
    public List<FeedItem> getHomeFeed() {
        return feedRepository.findAll();
    }

    // POST /api/v1/feed -> Saves a new item to MongoDB
    @PostMapping
    public FeedItem createFeedItem(@RequestBody FeedItem newItem) {
        return feedRepository.save(newItem);
    }
}