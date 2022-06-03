package ccc.data;

import ccc.models.Event;
import ccc.models.Performances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PerformancesJdbcTemplateRepositoryTest {

    @Autowired
    PerformancesJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Performances> performances = repository.findAll();
        assertNotNull(performances);
        assertTrue(performances.size()>=3);
    }

    @Test
    void shouldFindByPerformanceId()
    {
        Performances performance = repository.findById(1);
        assertNotNull(performance);
        assertEquals("Performance1",performance.getName());

    }


    @Test
    void shouldAddPerformance(){
        Performances p = makePerformance();
        Performances actual = repository.addPerformance(p);

        assertNotNull(actual);
        assertEquals(4, actual.getPerformancesId());
    }
    @Test
    void deleteTest(){

        assertTrue(repository.deleteById(1));
        assertFalse(repository.deleteById(1));
        assertFalse(repository.deleteById(99));
    }

    @Test
    void shouldUpdatePerformance(){
        Performances p = makePerformance();
        p.setDescription("Tawfik Test Repo");
        assertTrue(repository.update(p));
    }


    Performances makePerformance() {
        Performances performance = new Performances(1,"P-Test","D-Test");
        return performance;
    }



}