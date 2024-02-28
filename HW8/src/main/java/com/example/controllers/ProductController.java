package com.example.controllers;

import com.example.services.PaymentService;
import com.example.services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveProduct(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity) {
        try {
            // Логика резервирования товара на складе
            warehouseService.reserveProduct(productId, quantity);
            // Логика оплаты товара
            paymentService.processPayment(productId, quantity);
            return ResponseEntity.ok("Product reserved and payment processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to reserve product: " + e.getMessage());
        }
    }
}