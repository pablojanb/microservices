package com.example.sales_service.service;

import com.example.sales_service.dto.CartDTO;
import com.example.sales_service.model.Sale;
import com.example.sales_service.repository.ICartApi;
import com.example.sales_service.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService implements ISaleService{
    @Autowired
    private ISaleRepository iSaleRepository;

    @Autowired
    private ICartApi iCartApi;

    @Override
    @CircuitBreaker(name = "carts-service", fallbackMethod = "fallbackAddSale")
    @Retry(name = "carts-service")
    public Sale addSale(Long cart_id) {
        CartDTO cart = iCartApi.getCartById(cart_id);
        Sale sale = new Sale();
        sale.setSale_date(LocalDate.now());
        sale.setSale_total(cart.getCart_total());
        sale.setProductsList(cart.getProductsList());
        sale.setCart_id(cart_id);
        iSaleRepository.save(sale);
        iCartApi.emptyCart(cart_id);
        return sale;
    }

    @Override
    public Sale getSale(Long sale_id) {
        return iSaleRepository.findById(sale_id).orElse(null);
    }

    @Override
    public List<Sale> getSales() {
        return iSaleRepository.findAll();
    }

    public String fallbackAddSale(Throwable throwable){
        return "Try again later";
    }
}
