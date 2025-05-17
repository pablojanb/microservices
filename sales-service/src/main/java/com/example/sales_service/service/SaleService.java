package com.example.sales_service.service;

import com.example.sales_service.dto.CartDTO;
import com.example.sales_service.dto.ProductDTO;
import com.example.sales_service.dto.SalesByDate;
import com.example.sales_service.model.Sale;
import com.example.sales_service.repository.ICartApi;
import com.example.sales_service.repository.IProductApi;
import com.example.sales_service.repository.ISaleRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService{
    @Autowired
    private ISaleRepository iSaleRepository;

    @Autowired
    private ICartApi iCartApi;

    @Autowired
    private IProductApi iProductApi;

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

    public Sale fallbackAddSale(Throwable throwable){
        return null;
    }

    @Override
    public Sale getSale(Long sale_id) {
        return iSaleRepository.findById(sale_id).orElse(null);
    }

    @Override
    public List<Sale> getSales() {
        return iSaleRepository.findAll();
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackGetProductsBySale")
    @Retry(name = "products-service")
    public List<ProductDTO> getProductsBySale(Long sale_id) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        Sale sale = this.getSale(sale_id);
        for (Long product_id : sale.getProductsList()){
            ProductDTO productDTO = iProductApi.getProductById(product_id);
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    public List<ProductDTO> fallbackGetProductsBySale(Throwable throwable){
        return null;
    }

    @Override
    public Sale getBiggestSale() {
        List<Sale> saleList = iSaleRepository.getBiggestSale();
        return saleList.get(0);
    }

    @Override
    public SalesByDate getSalesByDate(LocalDate sale_date) {
        List<Sale> saleList = iSaleRepository.getSalesByDate(sale_date);
        SalesByDate salesByDate = new SalesByDate();
        salesByDate.setSales(saleList.size());
        double total = 0;
        for (Sale sale : saleList) {
            total += sale.getSale_total();
        }
        salesByDate.setTotal(total);
        return salesByDate;
    }
}
