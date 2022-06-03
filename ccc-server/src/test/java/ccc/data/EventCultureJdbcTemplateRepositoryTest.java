package ccc.data;

import ccc.models.Event;
import ccc.models.EventCulture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EventCultureJdbcTemplateRepositoryTest {

    @Autowired
    EventCultureJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<EventCulture> ec = repository.findAll();
        assertNotNull(ec);
        assertTrue(ec.size()>=3);
    }


    @Test
    void shouldFindEventCultureById()
    {
        EventCulture eventCulture = repository.findById(1);
        assertNotNull(eventCulture);
        assertEquals("Central American",eventCulture.getCultureName());

    }

    @Test
    void shouldFindEventCultureByName()
    {
        EventCulture eventCulture = repository.findByName("Asian");
        assertNotNull(eventCulture);
        assertEquals(2,eventCulture.getCultureId());

    }

    @Test
    void shouldAddEventCulture (){

        EventCulture eventCulture = MakeEventCulture();
        EventCulture actual = repository.add(eventCulture);
        assertNotNull(actual);

    }


    EventCulture MakeEventCulture() {
        EventCulture eventCulture = new EventCulture(4,"CultureNameTest","testFlag");
        return eventCulture;
    }

    // test Failed.
    @Test
    void shouldUpdateEventCulture(){
        EventCulture eventCulture = MakeEventCulture();
        eventCulture.setCultureId(1);
        eventCulture.setCultureName("Set");
        assertTrue(repository.update(eventCulture));
    }


//    @Test
//    void deleteTest(){
//
//        assertTrue(repository.deleteById(1));
//        assertFalse(repository.deleteById(1));
//        assertFalse(repository.deleteById(99));
//    }






}