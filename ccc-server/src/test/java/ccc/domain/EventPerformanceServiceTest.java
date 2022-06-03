package ccc.domain;

import ccc.data.EventPerformanceRepository;
import ccc.models.Event;
import ccc.models.EventPerformance;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventPerformanceServiceTest {
    @Autowired
    EventPerformanceService service;

    @MockBean
    EventPerformanceRepository repository;

    @Test
    void add() {
        EventPerformance eventPerformance = makeEventPerformance();
        Mockito.when(repository.add(eventPerformance)).thenReturn(eventPerformance);

        Result<EventPerformance> result = service.add(eventPerformance);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(eventPerformance, result.getPayload());
    }

    EventPerformance makeEventPerformance(){
        EventPerformance eventPerformance = new EventPerformance(6,1);
        return eventPerformance;
    }
}