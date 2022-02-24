package com.rei.interview.location;

import com.rei.interview.rs.Location;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationService {

    public List<Store> getNearbyStores(Location location) {
        return List.of(new Store("Seattle"), new Store("Issaquah"));
    }
}
