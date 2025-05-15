package com.example.sales_service.controller;

import com.example.sales_service.model.Sale;
import com.example.sales_service.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {
    @Autowired
    private ISaleService iSaleService;

    @PostMapping("/{cart_id}")
    public Sale addSale(@PathVariable Long cart_id){
        return iSaleService.addSale(cart_id);
    }

    @GetMapping("/{sale_id}")
    public Sale getSale(@PathVariable Long sale_id){
        return iSaleService.getSale(sale_id);
    }

    @GetMapping("")
    public List<Sale> getSale(){
        return iSaleService.getSales();
    }
}
