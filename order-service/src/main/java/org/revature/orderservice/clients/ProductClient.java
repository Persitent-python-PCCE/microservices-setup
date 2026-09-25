package org.revature.orderservice.clients;

import org.revature.orderservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/products")
    List<Product> getAllProducts();

    @PostMapping("/products")
    Product createProduct(@RequestBody Product pr);

    @PutMapping("/products/{id}")
    Product updateProduct(@PathVariable Long id, @RequestBody Product pr);
}
