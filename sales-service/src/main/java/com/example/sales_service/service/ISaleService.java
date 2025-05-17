package com.example.sales_service.service;

import com.example.sales_service.dto.ProductDTO;
import com.example.sales_service.dto.SalesByDate;
import com.example.sales_service.model.Sale;

import java.time.LocalDate;
import java.util.List;

public interface ISaleService {
    Sale addSale(Long cart_id);

    Sale getSale(Long sale_id);

    List<Sale> getSales();

    List<ProductDTO> getProductsBySale(Long sale_id);

    Sale getBiggestSale();

    SalesByDate getSalesByDate(LocalDate sale_date);
}
