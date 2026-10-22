package com.yash.skypulse_backend.dto;

import lombok.Data;

@Data
public class CartItemRequest {
    private String productId;
    private int quantity;
}