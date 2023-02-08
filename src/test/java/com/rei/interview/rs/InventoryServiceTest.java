package com.rei.interview.rs;


import com.rei.interview.inventory.InventoryService;
import com.rei.interview.location.LocationService;
import com.rei.interview.location.Store;
import com.rei.interview.product.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {
    private final LocationService locationService;

    private final InventoryService inventoryService;


    public InventoryServiceTest(){
        this.locationService = mock(LocationService.class);
        this.inventoryService = new InventoryService(locationService);
    }

    @Test
    public void testOnline(){
        Product product = new Product();
        product.setProductId("222222");
        product.setBrand("test");
        product.setDescription("test");
        product.setPrice(new BigDecimal(1));

        assertTrue(inventoryService.hasInventoryOnline(product, 5));

        product.setProductId("195748");
        assertFalse(inventoryService.hasInventoryOnline(product, 12));

        product.setProductId("163728");
        assertFalse(inventoryService.hasInventoryOnline(product, 1));
    }

    @Test
    public void testLocal(){
        Product product = new Product();
        product.setProductId("226688");
        product.setBrand("test");
        product.setDescription("test");
        product.setPrice(new BigDecimal(1));

        when(locationService.getNearbyStores(Location.SEATTLE)).thenReturn(List.of(new Store("Seattle"), new Store("Tacoma")));

        assertTrue(inventoryService.hasInventoryInNearbyStores(product, 20, Location.SEATTLE));
        assertFalse(inventoryService.hasInventoryInNearbyStores(product, 55, Location.SEATTLE));
    }

}
