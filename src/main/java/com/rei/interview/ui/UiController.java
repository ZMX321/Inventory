package com.rei.interview.ui;

import com.rei.interview.checkout.CartService;
import com.rei.interview.inventory.InventoryService;
import com.rei.interview.location.LocationService;
import com.rei.interview.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {

    private ProductService productService;
    private LocationService locationService;
    private InventoryService inventoryService;
    private CartService cartService;


    public UiController(
            ProductService productService,
            LocationService locationService,
            InventoryService inventoryService,
            CartService cartService) {
        this.productService = productService;
        this.locationService = locationService;
        this.inventoryService = inventoryService;
        this.cartService = cartService;

    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "index";
    }
}
