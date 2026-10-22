package com.yash.skypulse_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "orders") // "order" is a reserved keyword in SQL, so we must use "orders"
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    private String customerName; // Mocked for now until we add Authentication
    private double totalAmount;
    private String status; // e.g., "PAID", "SHIPPED"
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id") // Creates a foreign key in the order_items table
    private List<OrderItem> items;
}