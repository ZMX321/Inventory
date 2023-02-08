package com.rei.interview.checkout;

import java.math.BigDecimal;

import com.rei.interview.inventory.InventoryService;
import com.rei.interview.product.Product;
import com.rei.interview.product.ProductService;
import com.rei.interview.rs.Location;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartService {

    private final ProductService productService;
    private final InventoryService inventoryService;
    private final CartRepository cartRepository;



    @Autowired
    public CartService(ProductService productService, InventoryService inventoryService, CartRepository cartRepository) {
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.cartRepository = cartRepository;
    }

    public Cart addToCart(String cartId, Product product, int quantity, Location location) throws Exception {

        Cart cart;

        // do we have a valid product?
        if (!StringUtils.isNumeric(product.getProductId()) || product.getProductId().length() != Product.PRODUCT_ID_LENGTH || product.getDescription().isEmpty() || product.getPrice().compareTo(BigDecimal.ZERO) < 1) {
            throw new Exception("Invalid Product");
        }


        // is there enough inventory to sell this product?
        if (!inventoryService.hasInventoryOnline(product, quantity) && !inventoryService.hasInventoryInNearbyStores(product, quantity, location)) {
            throw new Exception("No inventory for this product");
        }

        // is there already a cart for this customer?
        if(cartId == null || (cart = cartRepository.getCart(cartId)) == null){
            cart = new Cart();
            cartRepository.addCart(cart);
        }

        //is this item already in the cart? If so add to the existing quantity
        Integer existingQuantity;
        if((existingQuantity = cart.getProducts().get(product)) == null){
            cart.getProducts().put(product, 0);
        }
        cart.getProducts().put(product, existingQuantity == null ? 0 : existingQuantity + quantity);



        return cart;
    }
}

