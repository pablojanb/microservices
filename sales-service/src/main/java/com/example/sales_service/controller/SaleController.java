package com.example.sales_service.controller;

import com.example.sales_service.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private ISaleService iSaleService;

    @PostMapping("/{cart_id}")
    public String addSale(@PathVariable Long cart_id){
        return iSaleService.addSale(cart_id);
    }
}
