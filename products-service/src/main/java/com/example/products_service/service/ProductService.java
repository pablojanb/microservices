package com.example.products_service.service;

import com.example.products_service.model.Product;
import com.example.products_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository iProductRepository;

    @Override
    public List<Product> getAllProducts() {
        return iProductRepository.findAll();
    }

    @Override
    public List<Product> getProductsLowStock() {
        List<Product> productList = iProductRepository.findAll();
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getStock() < 5) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }

    @Override
    public Product getProductById(Long id) {
        return iProductRepository.findById(id).orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        if (product.getBrand() == null || product.getPrice() < 0.0 || product.getName() == null || product.getStock() < 1) {
            return null;
        }
        return iProductRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        iProductRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product updatedProduct = this.getProductById(id);
        if (updatedProduct == null) {
            return null;
        }
        updatedProduct.setBrand(product.getBrand());
        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setStock(product.getStock());
        return this.addProduct(updatedProduct);
    }
}
