package com.rei.interview.product;

import com.rei.interview.rs.product.ProductDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean isValidProduct(Product product){
        // check if product is valid
        return !product.getDescription().isEmpty() && product.getPrice().compareTo(BigDecimal.ZERO) >= 1;
    }

    public Product getProduct(String productId) {
        return productRepository.getProduct(productId);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productRepository.getAll());
    }

    public List<Product> getProductsByBrand(String brandName) {
        return productRepository.getProductsByBrand(brandName);
    }

    /**
     * Populates the product repository with data from products.txt
     *
     * @throws IOException
     */
    @PostConstruct
    public void populateProducts() throws IOException {
        try(Reader in = new InputStreamReader(getClass().getResourceAsStream("/products.csv"))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("productId", "brand", "description", "price")
                    .withFirstRecordAsHeader()
                    .parse(in);

            for (CSVRecord record : records) {
                Product product = new Product();
                product.setProductId(record.get("productId"));
                product.setBrand(record.get("brand"));
                product.setDescription(record.get("description"));
                product.setPrice(new BigDecimal(record.get("price")));
                logger.info(product.toString());
                productRepository.addProduct(product);
            }
        }

        logger.info("Products loaded into product repository");
    }


    public String createProduct(Product product) {
        if(!isValidProduct(product)){
            throw new IllegalArgumentException("Wrong Product Input");
        }
        return productRepository.createProduct(product);
    }

    public List<String> createBatchProducts(List<Product> productList) {
        for (int i = 0; i < productList.size(); i++) {
            if(!isValidProduct(productList.get(i))){
                productList.set(i, null);
            }
        }
        return productRepository.createBatchProduct(productList);
    }
}
