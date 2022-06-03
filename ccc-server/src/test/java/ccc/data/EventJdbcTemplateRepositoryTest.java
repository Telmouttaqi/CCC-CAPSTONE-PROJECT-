package ccc.data;

import ccc.models.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventJdbcTemplateRepositoryTest {
    @Autowired

    EventJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Event> events = repository.findAll();
        assertNotNull(events);
        assertEquals(events.size(),10);
    }
    @Test
    void shouldFindUnApprove() {
        List<Event> events = repository.findUnApprove();
        assertNotNull(events);
        assertEquals(events.size(),1);
    }

    @Test
    void shouldFindByDate(){
        List<Event> events = repository.findByDate(LocalDate.parse("2022-06-08"));
        assertNotNull(events);
        assertEquals(3,events.size());
    }

    @Test
    void shouldFindOneWithUser(){
        List<Event> events = repository.findByUsername("john@smith.com");
        assertNotNull(events);
        assertTrue(events.size()>=7);
        assertEquals("Acanthaster planci",events.get(0).getEventName());
    }

    @Test
    void shouldFindCorrectEventWithIdEight(){
        Event event = repository.findByEventId(8);
        assertNotNull(event);
        assertEquals("Kobus defassa",event.getEventName());
    }

    @Test
    void shouldNotFindEventWithIdEleven(){
        Event event = repository.findByEventId(11);
        assertNull(event);
    }

    @Test
    void shouldAddEvent(){
        Event event = makeEvent();
        Event actual = repository.addEvent(event);

        assertNotNull(actual);
        assertEquals(11, actual.getEventId());
    }

    @Test
    void shouldUpdateEvent(){
        Event event = makeEvent();
        event.setEventId(2);
        assertTrue(repository.update(event));
    }
    @Test
    void shouldNotUpdateMissingEvent(){
        Event event = makeEvent();
        event.setEventId(20);
        assertFalse(repository.update(event));
    }

    @Test
    void deleteTest(){

        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
        assertFalse(repository.deleteById(99));
    }

    Event makeEvent(){
        Event event = new Event(0,"New Event", "07920 Fallview Circle, Gadsden, AL, 35905", LocalDate.now(),null, 50, "Music", "Asian", "john@smith.com");
        return event;
    }
}