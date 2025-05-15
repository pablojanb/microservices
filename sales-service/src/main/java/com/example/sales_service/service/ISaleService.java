package com.example.sales_service.service;

import com.example.sales_service.model.Sale;

import java.util.List;

public interface ISaleService {
    Sale addSale(Long cart_id);

    Sale getSale(Long sale_id);

    List<Sale> getSales();
}
