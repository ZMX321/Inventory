package com.rei.interview.rs.product;

import com.rei.interview.product.Product;
import com.rei.interview.product.ProductService;
import com.rei.interview.rs.product.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductWebService {

    private final ProductService productService;


    @Autowired
    public ProductWebService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{productId}")
    public ResponseEntity getProductById(@PathVariable String productId){
        Product product;
        if((product = productService.getProduct(productId)) == null){
            return new ResponseEntity("Product not found.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(new ProductDto(product), HttpStatus.OK);
    }

    @GetMapping("/brand/{brandName}")
    public ResponseEntity getProductsByBrand(@PathVariable String brandName){
        List<Product> products;
        if((products = productService.getProductsByBrand(brandName))== null){
            return new ResponseEntity("Products not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(transform(products), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity creatProduct(@RequestParam Product product){
        String newId;
        if((newId = productService.createProduct(product)) == null){
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(newId, HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity createBatchProducts(@RequestParam List<Product> productList){
        List<String> newIdList = productService.createBatchProducts(productList);
        return new ResponseEntity(newIdList, HttpStatus.CREATED);
    }


    private List<ProductDto> transform(List<Product> products){
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
    }


}
