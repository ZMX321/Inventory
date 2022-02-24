package com.rei.interview.checkout;

import com.rei.interview.util.Cache;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CartRepository {
    private Map<String, Cart> carts = new Cache<>();

    public void addCart(Cart cart) {
        carts.put(cart.getCartId().toString(), cart);
    }

    public Cart getCart(String cartId) {
        return carts.get(cartId);
    }
}
