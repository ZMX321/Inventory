package com.rei.interview.inventory;

import com.rei.interview.location.LocationService;
import com.rei.interview.location.Store;
import com.rei.interview.product.Product;
import com.rei.interview.rs.Location;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

@Component
public class InventoryService {

    private final LocationService locationService;

    private Map<String, Map<String, Integer>> inventory = null;

    @Autowired
    public InventoryService(LocationService locationService) {
        this.locationService = locationService;

    }

    @PostConstruct
    @Scheduled(fixedRate = 5000)
    public void readInInventory() throws IOException {
        inventory = new HashMap<>();
        try(Reader in = new InputStreamReader(getClass().getResourceAsStream("/product_inventory.csv"))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("productId", "location", "quantity")
                    .withFirstRecordAsHeader()
                    .parse(in);

            for (CSVRecord record : records) {
                String productId = record.get("productId");
                String location = record.get("location");
                int quantity = Integer.valueOf(record.get("quantity"));
                if(!inventory.containsKey(location)){
                    inventory.put(location, new HashMap<>());
                }
                inventory.get(location).put(productId, inventory.get(location).getOrDefault(productId, 0) + quantity);
                System.out.println(productId + "\t" + location + "\t" + quantity);
            }
        }
    }

    public boolean hasInventoryOnline(Product product, int quantity) {
        try {
            readInInventory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Integer> onlineInventory = inventory.get("ONLINE");
        return onlineInventory.containsKey(product.getProductId()) && onlineInventory.get(product.getProductId()) >= quantity;
    }

    public boolean hasInventoryInNearbyStores(Product product, int quantity, Location currentLocation) {
        try {
            readInInventory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Store store : locationService.getNearbyStores(currentLocation)){
            if(inventory.containsKey(store.getStoreName().toUpperCase()) && inventory.get(store.getStoreName().toUpperCase()).containsKey(product.getProductId())
                    && inventory.get(store.getStoreName().toUpperCase()).get(product.getProductId())- quantity > 1){
                return true;
            }
        }
        return false;
    }
}
