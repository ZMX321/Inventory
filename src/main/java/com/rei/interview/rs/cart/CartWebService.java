package com.rei.interview.rs.cart;

import com.rei.interview.checkout.Cart;
import com.rei.interview.checkout.CartService;
import com.rei.interview.product.Product;
import com.rei.interview.product.ProductService;
import com.rei.interview.rs.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartWebService {

    private ProductService productService;
    private CartService cartService;

    @Autowired
    public CartWebService(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    @ResponseBody
    public String test() {
        return "test";
    }

    @PostMapping("/{cartId}")
    @ResponseBody
    public CartDto addToCart(@PathParam("cartId") String cartId,
                                                    @RequestBody AddItem addItem) throws Exception {
        Product product = productService.getProduct(addItem.getProductId());
        Cart cart = cartService.addToCart(cartId, product, addItem.getQuantity(), addItem.getLocation());

        return transform(cart);
    }

    private CartDto transform(Cart cart) {
        CartDto dto = new CartDto();
        dto.setCartId(cart.getCartId().toString());

        List<CartItem> dtoItems = new ArrayList<>();

        for(Map.Entry<Product,Integer> entry : cart.getProducts().entrySet()) {
            dtoItems.add(new CartItem(entry.getKey().getProductId(), entry.getValue()));
        }

        dto.setItems(dtoItems);

        return dto;
    }

    private ProductDto transform(Product product) {
        ProductDto dto = new ProductDto();
        dto.setProductId(product.getProductId());
        dto.setDescription(product.getDescription());
        dto.setBrand(product.getBrand());
        return dto;
    }

}
