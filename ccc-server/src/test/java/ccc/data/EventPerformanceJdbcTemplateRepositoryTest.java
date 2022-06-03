package ccc.data;

import ccc.models.Event;
import ccc.models.EventPerformance;
import ccc.models.Rsvp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventPerformanceJdbcTemplateRepositoryTest {

    @Autowired
    EventPerformanceJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<EventPerformance> eventPerformances = repository.findAll();
        assertNotNull(eventPerformances);
        assertTrue(eventPerformances.size()>= 5);
    }

    @Test
    void ShouldFindById() {
        EventPerformance eventPerformance = repository.findById(2, 3);
        assertNotNull(eventPerformance);

    }

        @Test
    void shouldAdd(){
        EventPerformance eventPerformance = new EventPerformance(8,2);

        EventPerformance returned = repository.add(eventPerformance);

        assertEquals(8, eventPerformance.getEventId());
        assertEquals(2, eventPerformance.getPerformanceId());

    }

}