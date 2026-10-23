package com.yash.skypulse_backend.controller;

import com.yash.skypulse_backend.dto.CartItemRequest;
import com.yash.skypulse_backend.dto.CheckoutRequest;
import com.yash.skypulse_backend.model.Order;
import com.yash.skypulse_backend.model.OrderItem;
import com.yash.skypulse_backend.model.Product;
import com.yash.skypulse_backend.repository.OrderRepository;
import com.yash.skypulse_backend.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/shop")
public class ShopController {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @PostMapping("/checkout")
    @Transactional // Ensures that if anything fails, the whole transaction rolls back!
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request) {
        double totalAmount = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        
        for (CartItemRequest itemReq : request.getItems()) {
            // Find the product in the database
            Product product = productRepository.findById(itemReq.getProductId()).orElse(null);
            
            // Check if product exists and if we have enough stock
            if (product == null || product.getStockQuantity() < itemReq.getQuantity()) {
                return ResponseEntity.badRequest().body("Insufficient stock for product ID: " + itemReq.getProductId());
            }
            
            // Deduct the stock
            product.setStockQuantity(product.getStockQuantity() - itemReq.getQuantity());
            productRepository.save(product); // Update the DB
            
            // Create an OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(itemReq.getQuantity());
            orderItem.setPriceAtPurchase(product.getPrice());
            orderItems.add(orderItem);
            
            // Add to total cost
            totalAmount += (product.getPrice() * itemReq.getQuantity());
        }
        
        // Create and save the final Order
        Order newOrder = new Order();
        newOrder.setCustomerName(request.getUsername() != null ? request.getUsername() : "YashCommander");
        newOrder.setTotalAmount(totalAmount);
        newOrder.setStatus("PAID");
        newOrder.setItems(orderItems);
        
        Order savedOrder = orderRepository.save(newOrder);
        
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/orders/{username}")
    public List<Order> getUserOrders(@PathVariable String username) {
        return orderRepository.findByCustomerName(username);
    }
}