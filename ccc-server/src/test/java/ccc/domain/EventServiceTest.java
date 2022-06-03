package ccc.domain;

import ccc.data.EventRepository;
import ccc.models.Event;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class EventServiceTest {

    @Autowired
    EventService service;

    @MockBean
    EventRepository repository;

    @Test
    void shouldFindByEventId(){
        Event event = makeEvent();
        Mockito.when(repository.findByEventId(1)).thenReturn(event);

        Result<Event> result = service.findByEventId(1);
        assertEquals(event, result.getPayload());
    }

    @Test
    void shouldNotFindByEventId(){
        Event event = makeEvent();
        Mockito.when(repository.findByEventId(2)).thenReturn(null);

        Result<Event> result = service.findByEventId(2);

        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals("Unable to find event with id 2.", result.getMessages().get(0));
        assertNull(result.getPayload());
    }

    @Test
    void shouldAdd(){
        Event event = makeEvent();
        Event newEvent = makeEvent();
        newEvent.setEventId(0);
        Mockito.when(repository.addEvent(newEvent)).thenReturn(event);

        Result<Event> result = service.addEvent(newEvent);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(event, result.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid(){
        Event event = makeEvent();
        event.setStartDate(null);

        Result<Event> result = service.addEvent(event);
        assertEquals(ResultType.INVALID, result.getType());
        System.out.println(result.getMessages());
        assertEquals(1, result.getMessages().size());
        assertEquals("An Event requires a starting date.", result.getMessages().get(0));
    }

    @Test
    void shouldUpdate(){
        Event event = makeEvent();
        event.setEventId(8);
        event.setUsername("sally@jones.com");

        Mockito.when(repository.findByEventId(8)).thenReturn(event);
        Mockito.when(repository.update(any())).thenReturn(true);

        Result<Event> result = service.updateEvent(event);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateIfNotCreator(){
        Event event = makeEvent();

        event.setUsername("sally@jones.com");
        Event oldEvent = makeEvent();

        Mockito.when(repository.findByEventId(1)).thenReturn(oldEvent);

        Result<Event> result = service.updateEvent(event);
        assertEquals(ResultType.FORBIDDEN, result.getType());
        assertEquals("UNAUTHORIZED!", result.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateIfInvalidEvent(){
        Event event = makeEvent();
        event.setCapacity(0);
        event.setStartDate(LocalDate.now().plusDays(-1));

        event.setEventId(8);
        event.setUsername("sally@jones.com");
        Mockito.when(repository.findByEventId(8)).thenReturn(event);

        Result<Event> result = service.updateEvent(event);
        System.out.println(result.getMessages());
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("An Event needs to happen in the future.", result.getMessages().get(0));
        assertEquals("An Event requires greater capacity.", result.getMessages().get(1));
    }

    Event makeEvent(){
        Event event = new Event(1,"New Event", "07920 Fallview Circle, Gadsden, AL, 35905", LocalDate.now(),null, 50, "Music", "Asian", "john@smith.com");
        return event;
    }
}