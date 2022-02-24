package com.rei.interview.rs.cart;

import java.util.List;

public class CartDto {

    private String cartId;
    private List<CartItem> items;


    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
