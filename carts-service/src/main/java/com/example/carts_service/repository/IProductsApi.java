package com.example.carts_service.repository;

import com.example.carts_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-service")
public interface IProductsApi {
    @GetMapping("/products/{id}")
    public ProductDTO getProductById(@PathVariable Long id);
}
