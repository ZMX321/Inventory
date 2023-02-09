package com.rei.interview.rs.product;

import com.rei.interview.product.Product;

import java.math.BigDecimal;

public class ProductDto {

    private String productId;
    private String brand;
    private BigDecimal price;

    public ProductDto(Product product){
        this.productId = product.getProductId();
        this.brand = product.getBrand();
        this.price = product.getPrice();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.productId;
    }
}
