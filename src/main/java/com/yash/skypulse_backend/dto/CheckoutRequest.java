package com.yash.skypulse_backend.dto;

import lombok.Data;
import java.util.List;

@Data
public class CheckoutRequest {
    private String username;
    private List<CartItemRequest> items;
}