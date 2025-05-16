package com.example.carts_service.service;

import com.example.carts_service.model.Cart;

public interface ICartService {

    Cart getCartById(Long cart_id);

    Cart addProductToCart(Long cart_id, Long product_id);

    Cart addCart();

    Cart addCart(Cart cart);

    Cart removeProductFromCart(Long cart_id, Long product_id);

    Cart emptyCart(Long id);
}
