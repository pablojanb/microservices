package com.example.sales_service.controller;

import com.example.sales_service.dto.ProductDTO;
import com.example.sales_service.dto.SalesByDate;
import com.example.sales_service.model.Sale;
import com.example.sales_service.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/biggest_sale")
    public Sale getBiggestSale(){
        return iSaleService.getBiggestSale();
    }

    @GetMapping("")
    public List<Sale> getSale(){
        return iSaleService.getSales();
    }

    @GetMapping("/products/{sale_id}")
    public List<ProductDTO> getProductsBySale(@PathVariable Long sale_id){
        return iSaleService.getProductsBySale(sale_id);
    }

    @GetMapping("/date/{sale_date}")
    public SalesByDate getSalesByDate(@PathVariable LocalDate sale_date){
        return iSaleService.getSalesByDate(sale_date);
    }
}
