package ccc.domain;

import ccc.data.EventCategoryRepository;
import ccc.data.PerformancesRepository;
import ccc.models.Event;
import ccc.models.Performances;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class PerformancesServiceTest {

    @Autowired
    PerformancesService service;

    @MockBean
    PerformancesRepository repository;

    @Test
    void shouldFindByEventId(){
        Performances performance = makePerformance();
        Mockito.when(repository.findById(1)).thenReturn(performance);

        Performances result = service.findById(1);
        assertEquals(performance, result);
    }



    @Test
    void shouldAdd(){
        Performances performance = makePerformance();
        Performances newPerformance = makePerformance();
        newPerformance.setPerformancesId(0);
        Mockito.when(repository.addPerformance(newPerformance)).thenReturn(performance);

        Result<Performances> result = service.addPerformance(newPerformance);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(performance, result.getPayload());
    }


    @Test
    void shouldUpdate(){
        Performances performance = makePerformance();
        performance.setPerformancesId(4);

        Mockito.when(repository.findById(4)).thenReturn(performance);
        Mockito.when(repository.update(any())).thenReturn(true);

        Result<Performances> result = service.updatePerformance(performance);
        assertEquals(ResultType.SUCCESS, result.getType());
    }



    Performances makePerformance() {
        Performances performance = new Performances(1,"P-Test","D-Test");
        return performance;
    }


}