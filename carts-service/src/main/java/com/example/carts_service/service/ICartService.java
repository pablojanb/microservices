package com.example.carts_service.service;

import com.example.carts_service.model.Cart;

public interface ICartService {

    Cart getCartById(Long cart_id);

    String addProductToCart(Long cart_id, Long product_id);

    void addCart(Cart cart);
}
