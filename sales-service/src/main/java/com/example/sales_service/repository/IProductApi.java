package com.example.sales_service.repository;

import com.example.sales_service.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name = "products-service")
public interface IProductApi {
    @GetMapping("/products/{product_id}")
    public ProductDTO getProductById(@PathVariable Long product_id);
}
