package ccc.data;

import ccc.models.EventCategory;
import ccc.models.Location;
import ccc.models.Performances;

import java.util.List;

public interface LocationRepository {

    List <Location> findAll();

    Location findById(int locationId);

    Location findByZipCode(String zipCode);

    Location add(Location location);

    Boolean update(Location location);

    Boolean deleteById(int locationId);

    Location findByAddress(Location location);
}
