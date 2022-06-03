package ccc.controller;


import ccc.domain.LocationService;
import ccc.domain.Result;
import ccc.models.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final LocationService service;

    public LocationController(LocationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Location> findAll() {
        return service.findAll();
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<Object> findById(@PathVariable int locationId) {

        Location location = service.findById(locationId);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(location);
    }
    @GetMapping("/individual/{fullAddress}")
    public ResponseEntity<Object> findByAddress(@PathVariable String fullAddress) {
        String[] temp = fullAddress.split(",");

        Location location = new Location();
        location.setAddress(temp[0].trim());
        location.setCity(temp[1].trim());
        location.setState(temp[2].trim());
        location.setZipCode(temp[3].trim());

        location = service.findByAddress(location);
        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(location);
    }

    @PostMapping
    public ResponseEntity<Object> addLocation(@RequestBody Location location) {
        Result<Location> result = service.addLocation(location);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<Object> update(@PathVariable int locationId, @RequestBody Location location) {
        if (locationId != location.getLocationId() ) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Location> result = service.updateLocation(location);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }


    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteById(@PathVariable int locationId) {
        if (service.deleteById(locationId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }










}
