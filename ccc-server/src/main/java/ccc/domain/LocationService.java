package ccc.domain;


import ccc.data.LocationRepository;
import ccc.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepository repository;

    public LocationService(LocationRepository repository) {
        this.repository = repository;
    }

    public List<Location> findAll() {
        return repository.findAll();
    }

    public Location findById(int locationId) {
        return repository.findById(locationId);
    }

    public Location findByZipCode(String zipCode) {
        return repository.findByZipCode(zipCode);
    }
    public Location findByAddress(Location location) {
        return repository.findByAddress(location);
    }

    public Result<Location> addLocation(Location location){
        Result<Location> result = validate(location);
        if (!result.isSuccess()) {
            return result;
        }

        if (location.getLocationId() != 0) {
            result.addMessage("Location Id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        location = repository.add(location);
        result.setPayload(location);
        return result;
    }


    private Result<Location> validate(Location location){
        Result<Location> result = new Result<>();

        if (location == null) {
            result.addMessage("Location Cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(location.getAddress())) {
            result.addMessage("Address is required.", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(location.getCity())) {
            result.addMessage("City is required.", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(location.getState())) {
            result.addMessage("State is required.", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(location.getZipCode())) {
            result.addMessage("Zip Code is required.", ResultType.INVALID);
        }


        return result;
    }

    public Result<Location> updateLocation(Location location){
        Result<Location> result = new Result<>();


        result = validate(location);
        if (!result.isSuccess()) {
            return result;
        }


        if (location.getLocationId() <= 0) {
            result.addMessage("Location Id must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(location)) {
            String msg = String.format("Location with id %d is not found.", location.getLocationId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int locationId) {
        return repository.deleteById(locationId);
    }



}
