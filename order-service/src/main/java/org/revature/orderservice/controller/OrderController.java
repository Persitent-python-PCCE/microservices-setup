package org.revature.orderservice.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.aspectj.weaver.ast.Or;
import org.revature.orderservice.clients.ProductClient;
import org.revature.orderservice.dto.Product;
import org.revature.orderservice.models.Order;
import org.revature.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService service;
    private final ProductClient client;

    public OrderController(OrderService os, ProductClient client){
        this.service = os;
        this.client = client;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<>(service.getAllOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        return new ResponseEntity<>(service.saveOrder(order), HttpStatus.CREATED);
    }

    @GetMapping("/products")
    @CircuitBreaker(name = "productService", fallbackMethod = "productServiceFallback")
    public ResponseEntity<List<Product>> getProductsFromProductService(){
        List<Product> response = client.getAllProducts();
        Product pr = client.updateProduct(1L, new Product(1L, "shoes", 2300.00, 10));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> productServiceFallback(Exception e) {
        // Fallback response when the product-service is down or failing
        List<Product> fallbackProducts = java.util.Collections.singletonList(
                new Product(0L, "Fallback Product - Service Unavailable", 0.0, 0)
        );
        return new ResponseEntity<>(fallbackProducts, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
