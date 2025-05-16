package com.example.carts_service.service;

import com.example.carts_service.dto.ProductDTO;
import com.example.carts_service.model.Cart;
import com.example.carts_service.repository.ICartRepository;
import com.example.carts_service.repository.IProductsApi;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService implements ICartService{
    @Autowired
    private ICartRepository iCartRepository;

    @Autowired
    private IProductsApi iProductsApi;

    @Override
    public Cart getCartById(Long cart_id) {
        return iCartRepository.findById(cart_id).orElse(null);
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackProductToCart")
    @Retry(name = "products-service")
    public Cart addProductToCart(Long cart_id, Long product_id) {
        Cart cart = this.getCartById(cart_id);
        ProductDTO productDTO = iProductsApi.getProductById(product_id);
        if (cart == null || productDTO == null) {
            return null;
        }
        cart.getProductsList().add(productDTO.getProduct_id());
        cart.setCart_total(cart.getCart_total() + productDTO.getPrice());
        this.addCart(cart);
        return cart;
    }

    public Cart fallbackProductToCart(Throwable throwable){
        return null;
    }

    @Override
    public Cart addCart() {
        Cart cart = new Cart();
        return iCartRepository.save(cart);
    }

    @Override
    public Cart addCart(Cart cart) {
        return iCartRepository.save(cart);
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackProductToCart")
    @Retry(name = "products-service")
    public Cart removeProductFromCart(Long cart_id, Long product_id) {
        Cart cart = this.getCartById(cart_id);
        ProductDTO productDTO = iProductsApi.getProductById(product_id);
        boolean deleted = cart.getProductsList().removeIf(id -> product_id == id);
        if (productDTO == null || !deleted) {
            return null;
        }
        cart.setCart_total(cart.getCart_total()-productDTO.getPrice());
        return this.addCart(cart);
    }

    @Override
    public Cart emptyCart(Long id) {
        Cart cart = this.getCartById(id);
        cart.setProductsList(new ArrayList<>());
        cart.setCart_total(0.0);
        return this.addCart(cart);
    }
}
