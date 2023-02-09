package com.rei.interview.product;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.rei.interview.util.Cache;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {

    private Map<String, Product> products = new Cache<>();

    private Map<String, List<Product>> productsBrand = new Cache<>();

    public void addProduct(Product product) {

        products.put(product.getProductId(), product);

        //todo: in case of duplicate product
        if(!productsBrand.containsKey(product.getBrand())){
            productsBrand.put(product.getBrand(), new LinkedList<>());
        }
        productsBrand.get(product.getBrand()).add(product);
    }

    public Product getProduct(String productId) {
        return products.getOrDefault(productId, null);
    }

    public Collection<Product> getAll() {
        return products.values();
    }

    public List<Product> getProductsByBrand(String brandName) {
        return productsBrand.getOrDefault(brandName, null);
    }

    public String createProduct(Product product) {
        String newId = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));
        while(products.containsKey(newId)){
            newId = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 5)));
        }
        product.setProductId(newId);
        addProduct(product);
        return newId;
    }

    public List<String> createBatchProduct(List<Product> productList) {
        String[] newIdArr = new String[productList.size()];
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i) != null){
                newIdArr[i] = createProduct(productList.get(i));
            }else{
                newIdArr[i] = null;
            }
        }

        return Arrays.asList(newIdArr);
    }
}
