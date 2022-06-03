package ccc.data;

import ccc.models.EventCulture;
import ccc.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LocationJdbcTemplateRepositoryTest {

    @Autowired
    LocationJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void shouldFindAll() {
        List<Location> location = repository.findAll();
        assertNotNull(location);
        assertEquals(location.size(),10);
    }

    @Test
    void shouldFindLocationById()
    {
        Location location = repository.findById(1);
        assertNotNull(location);
        assertEquals("DC",location.getState());

    }

    @Test
    void shouldFindLocationByZipCode()
    {
        Location location = repository.findByZipCode("20299");
        assertNotNull(location);
        assertEquals("Washington",location.getCity());

    }

    @Test
    void shouldAddLocation (){
        Location location = makeLocation();
        Location actual = repository.add(location);
        assertNotNull(actual);

    }

    Location makeLocation() {

        Location location = new Location(11,"testAddress","TestCity","TestState","36985");
        return location;
    }



}