package com.example.carts_service.controller;

import com.example.carts_service.model.Cart;
import com.example.carts_service.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private ICartService iCartService;

    @PostMapping("/{cart_id}/product/{product_id}")
    public Cart addProductToCart(@PathVariable Long cart_id,
                                   @PathVariable Long product_id){
        return iCartService.addProductToCart(cart_id, product_id);
    }

    @GetMapping("/{cart_id}")
    public Cart getCartById(@PathVariable Long cart_id) {
        return iCartService.getCartById(cart_id);
    }

    @PostMapping("")
    public Cart addCart(){
        return iCartService.addCart();
    }

    @PutMapping("/{cart_id}")
    public Cart emptyCart(@PathVariable Long cart_id){
        return iCartService.emptyCart(cart_id);
    }

    @DeleteMapping("/{cart_id}/product/{product_id}")
    public Cart removeProductFromCart(@PathVariable Long cart_id,
                            @PathVariable Long product_id){
        return iCartService.removeProductFromCart(cart_id, product_id);
    }
}
