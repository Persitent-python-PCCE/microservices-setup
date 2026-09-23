package org.revature.productservice.service;


import org.revature.productservice.models.Product;
import org.revature.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repo){
        this.repository = repo;
    }

    public Product createProduct(Product product){
        return repository.save(product);
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product getProductById(Long id){
        Optional<Product> pr = repository.findById(id);
        return pr.get();
    }

    public Product updateProduct(Long id, Product product){
        Product existing_product = repository.findById(id).orElseThrow(
                ()->new RuntimeException("Product Not Found")
        );

        existing_product.setName(product.getName());
        existing_product.setPrice(product.getPrice());
        existing_product.setQuantity(product.getQuantity());

        return repository.save(existing_product);
    }

    public void deleteProduct(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }else{
            throw new RuntimeException("Product not found");
        }
    }

}
