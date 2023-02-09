package com.rei.interview.rs;

import com.rei.interview.product.Product;
import com.rei.interview.product.ProductService;
import com.rei.interview.rs.cart.CartDto;
import com.rei.interview.rs.product.ProductDto;
import com.rei.interview.rs.product.ProductWebService;
import com.sun.net.httpserver.HttpsServer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductWebServiceTest {

    private final ProductService productService;

    private final ProductWebService productWebService;

    @Autowired
    public ProductWebServiceTest(){
        this.productService = mock(ProductService.class);
        this.productWebService = new ProductWebService(productService);
    }

    @Test
    public void testProductGet(){
        Product product = new Product();
        product.setProductId("000001");
        product.setBrand("test");
        product.setDescription("test");
        product.setPrice(new BigDecimal(1.0));

        when(productService.getProduct("000001")).thenReturn(product);
        ResponseEntity<ProductDto> successEntity = productWebService.getProductById("000001");

        assertEquals(HttpStatus.OK, successEntity.getStatusCode());
        assertEquals(product.getBrand(), successEntity.getBody().getBrand());

        when(productService.getProduct("000002")).thenReturn(null);
        ResponseEntity<ProductDto> failEntity = productWebService.getProductById("000002");

        assertEquals(HttpStatus.NOT_FOUND, failEntity.getStatusCode());
    }

    @Test
    public void testProductsGetByBrand(){
        Product p1 = new Product();
        p1.setProductId("000001");
        p1.setBrand("test");
        p1.setDescription("test");
        p1.setPrice(new BigDecimal(1.0));

        Product p2 = new Product();
        p2.setProductId("000002");
        p2.setBrand("test");
        p2.setDescription("test");
        p2.setPrice(new BigDecimal(1.0));

        when(productService.getProductsByBrand("test")).thenReturn(List.of(p1,p2));
        ResponseEntity<List<ProductDto>> successEntity = productWebService.getProductsByBrand("test");

        assertEquals(HttpStatus.OK, successEntity.getStatusCode());
        assertEquals(2, successEntity.getBody().size());

        when(productService.getProductsByBrand("other")).thenReturn(null);
        ResponseEntity<ProductDto> failEntity = productWebService.getProductsByBrand("other");

        assertEquals(HttpStatus.NOT_FOUND, failEntity.getStatusCode());

    }

    @Test
    public void testProductCreate(){
        Product product = new Product();
        product.setBrand("test");
        product.setDescription("test");
        product.setPrice(new BigDecimal(1.0));

        when(productService.createProduct(product)).thenReturn("000001");
        ResponseEntity<String> successEntity = productWebService.creatProduct(product);

        assertEquals(HttpStatus.CREATED, successEntity.getStatusCode());
        assertEquals("000001", successEntity.getBody());


        when(productService.createProduct(product)).thenReturn(null);
        ResponseEntity<ProductDto> failEntity = productWebService.creatProduct(product);
        assertEquals(HttpStatus.BAD_REQUEST, failEntity.getStatusCode());
    }

    @Test
    public void testBatchProductCreate(){
        Product p1 = new Product();
        p1.setProductId("000001");
        p1.setBrand("test");
        p1.setDescription("test");
        p1.setPrice(new BigDecimal(1.0));

        Product p2 = new Product();
        p2.setProductId("000002");
        p2.setBrand("test");
        p2.setPrice(new BigDecimal(1.0));

        List<String> returnList = new LinkedList<>();
        returnList.add("000001");
        returnList.add(null);

        when(productService.createBatchProducts(List.of(p1,p2))).thenReturn(returnList);
        ResponseEntity<String> successEntity = productWebService.createBatchProducts(List.of(p1,p2));

        assertEquals(HttpStatus.CREATED, successEntity.getStatusCode());
    }



}
