package com.rei.interview.rs;

import com.rei.interview.checkout.Cart;
import com.rei.interview.checkout.CartRepository;
import com.rei.interview.checkout.CartService;
import com.rei.interview.inventory.InventoryService;
import com.rei.interview.product.Product;
import com.rei.interview.product.ProductService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartTest {

    private final ProductService productService;

    private final InventoryService inventoryService;

    private final CartRepository cartRepository;

    private final CartService cartService;



   public CartTest(){
       this.cartRepository = mock(CartRepository.class);
       this.productService = mock(ProductService.class);
       this.inventoryService = mock(InventoryService.class);
       this.cartService = new CartService(productService, inventoryService, cartRepository);
   }

   @Test
   public void testInValidProduct(){
       Product product = new Product();
       //set a wrong id
       product.setProductId("12345");
       product.setBrand("test");
       product.setDescription("test");
       product.setPrice(new BigDecimal(1.0));
       assertThrows(Exception.class, () -> cartService.addToCart("001", product, 1, Location.SEATTLE));

       //correct id and set description as null
       product.setProductId("123456");
       product.setDescription(null);
       assertThrows(Exception.class, () -> cartService.addToCart("001", product, 1, Location.SEATTLE));

       //correct description and set wrong price
       product.setDescription("test");
       product.setPrice(new BigDecimal(-1));
       assertThrows(Exception.class, () -> cartService.addToCart("001", product, 1, Location.SEATTLE));

   }

   @Test
   public void testInventory(){
       Product product = new Product();
       product.setProductId("123456");
       product.setBrand("test");
       product.setDescription("test");
       product.setPrice(new BigDecimal(1));

       //when no enough inventory online
       when(inventoryService.hasInventoryOnline(product, 2)).thenReturn(false);

       //when no enough inventory local
       when(inventoryService.hasInventoryInNearbyStores(product, 1, Location.SEATTLE)).thenReturn(false);

       assertThrows(Exception.class, () -> cartService.addToCart("001", product, 2, Location.SEATTLE));
   }

   @Test
   public void testAddCart() {
       Product product = new Product();
       product.setProductId("123456");
       product.setBrand("test");
       product.setDescription("test");
       product.setPrice(new BigDecimal(1));

       Cart cart = new Cart();
       cart.getProducts().put(product, 2);

       when(cartRepository.getCart("001")).thenReturn(cart);
       when(cartRepository.getCart("002")).thenReturn(null);

       try{
           //test null cartId input
           assertEquals(1, Optional.ofNullable(cartService.addToCart(null, product, 1, Location.SEATTLE).getProducts().get(product)));
           //test null cart get from cartRepository
           assertEquals(1, Optional.ofNullable(cartService.addToCart("002", product, 1, Location.SEATTLE).getProducts().get(product)));
           //test product add under the situation of has same product added before
           assertEquals(3, Optional.ofNullable(cartService.addToCart("001", product, 1, Location.SEATTLE).getProducts().get(product)));
       }catch (Exception e){
           System.out.println(e.getMessage());
       }

   }


}
