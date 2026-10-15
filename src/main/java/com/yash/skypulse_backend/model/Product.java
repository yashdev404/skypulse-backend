package com.yash.skypulse_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String imageUrl;
    private int stockQuantity;
    private boolean isBestseller;
    private double rating;
    private int reviewCount;
    private Double originalPrice; // Use Double (object) instead of double (primitive) so it can be null
}