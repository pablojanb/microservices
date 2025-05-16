package com.example.products_service.service;

import com.example.products_service.model.Product;

import java.util.List;

public interface IProductService {

    List<Product> getAllProducts();

    List<Product> getProductsLowStock();

    Product getProductById(Long id);

    Product addProduct(Product product);

    void deleteProduct(Long id);

    Product updateProduct(Long id, Product product);
}
