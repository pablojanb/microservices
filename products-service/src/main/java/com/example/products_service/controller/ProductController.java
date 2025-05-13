package com.example.products_service.controller;

import com.example.products_service.model.Product;
import com.example.products_service.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @Value("${server.port}")
    private int server;

    @GetMapping("")
    public List<Product> getAllProducts(){
        return iProductService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        System.out.println("Server: " + server);
        return iProductService.getProductById(id);
    }

}
