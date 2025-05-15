package com.example.products_service.service;

import com.example.products_service.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    void addProduct(Product product);

    void deleteProduct(Long id);

    void updateProduct(Long id, Product product);
}
