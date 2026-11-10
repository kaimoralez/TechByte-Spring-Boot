package com.dt2.techbyte.controller;

import com.dt2.techbyte.model.Product;
import com.dt2.techbyte.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    private final ProductRepository productRepository;

    public Controller(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    @GetMapping("get")
    public List<Product> products(){
        return productRepository.findAll();
    }

    @PostMapping("item")
    public Product postItem(@RequestBody Product product){
        return productRepository.save(product);
    }

    @PostMapping("itens")
    public List<Product> postItens(@RequestBody List<Product> products){
        return productRepository.saveAll(products);
    }

}
