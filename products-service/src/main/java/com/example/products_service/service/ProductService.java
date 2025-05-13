package com.example.products_service.service;

import com.example.products_service.model.Product;
import com.example.products_service.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Product getProductById(Long id) {
        return iProductRepository.findById(id).orElse(null);
    }
}
