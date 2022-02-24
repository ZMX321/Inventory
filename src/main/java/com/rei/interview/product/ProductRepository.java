package com.rei.interview.product;

import com.rei.interview.util.Cache;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
public class ProductRepository {

    private Map<String, Product> products = new Cache<>();

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    public Collection<Product> getAll() {
        return products.values();
    }
}
