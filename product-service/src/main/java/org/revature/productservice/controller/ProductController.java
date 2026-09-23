package org.revature.productservice.controller;

import org.revature.productservice.models.Product;
import org.revature.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){//jackson-databind
        Product res = service.createProduct(product);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> pr = service.getAllProducts();
        return new ResponseEntity<>(pr, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Product> getProductById( @RequestParam Long id, @RequestHeader("Authorization") String token){
        System.out.println(token);
        Product res = service.getProductById(id);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Product res = service.updateProduct(id, product);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return new ResponseEntity<>("Product deleted succsesful", HttpStatus.OK);
    }



}
