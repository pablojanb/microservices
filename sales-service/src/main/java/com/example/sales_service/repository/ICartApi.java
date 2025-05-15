package com.example.sales_service.repository;

import com.example.sales_service.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "carts-service")
public interface ICartApi {
    @GetMapping("/carts/{id}")
    public CartDTO getCartById(@PathVariable Long id);

    @PutMapping("/carts/{cart_id}")
    public String emptyCart(@PathVariable Long cart_id);
}
