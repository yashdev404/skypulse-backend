package com.yash.skypulse_backend.controller;

import com.yash.skypulse_backend.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shop")
public class ShopController {

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return Arrays.asList(
            new Product("1", "Orion SkyQuest XT8 Classic Dobsonian", "Highly detailed 1:100 scale model.", 459.99, "Telescopes", "https://developer.android.com/static/images/brand/android-logo.png", 50, true, 4.8, 1240, 499.99),
            new Product("2", "NASA Vintage Worm Logo Hoodie", "100% cotton, glow in the dark.", 45.00, "Apparel", "https://developer.android.com/static/images/brand/android-logo.png", 200, true, 4.9, 856, null),
            new Product("3", "Authentic Campo del Cielo Meteorite", "Perfect beginner telescope for planetary viewing.", 120.00, "Meteorites", "https://developer.android.com/static/images/brand/android-logo.png", 10, false, 4.7, 92, 150.00),
            new Product("4", "James Webb Space Telescope Model", "High-res glossy print (24x36).", 85.50, "Models", "https://developer.android.com/static/images/brand/android-logo.png", 100, false, 4.6, 215, 95.00)
        );
    }
}