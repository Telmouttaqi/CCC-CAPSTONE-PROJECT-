package ccc.domain;

import ccc.data.EventCategoryRepository;
import ccc.data.LocationRepository;
import ccc.models.Event;
import ccc.models.Location;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class LocationServiceTest {

    @Autowired
    LocationService service;

    @MockBean
    LocationRepository repository;

    @Test
    void shouldFindByEventId(){
        Location location = makeLocation();
        Mockito.when(repository.findById(1)).thenReturn(location);

        Location result = service.findById(1);
        assertEquals(location, result);
    }



    @Test
    void shouldAdd(){
        Location location = makeLocation();
        Location newLocation = makeLocation();

        newLocation.setLocationId(0);
        Mockito.when(repository.add(newLocation)).thenReturn(location);

        Result<Location> result = service.addLocation(newLocation);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(location, result.getPayload());
    }


    Location makeLocation() {

        Location location = new Location(11,"testAddress","TestCity","TestState","36985");
        return location;
    }




}