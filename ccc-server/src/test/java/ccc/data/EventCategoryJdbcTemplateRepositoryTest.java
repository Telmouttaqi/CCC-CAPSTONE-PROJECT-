package ccc.data;

import ccc.models.EventCategory;
import ccc.models.EventCulture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EventCategoryJdbcTemplateRepositoryTest {

    @Autowired
    EventCategoryJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }


    @Test
    void shouldFindAll() {
        List<EventCategory> ec = repository.findAll();
        assertNotNull(ec);
        assertTrue(ec.size()>= 4);
    }

    @Test
    void shouldFindEventCategoryById()
    {
        EventCategory eventCategory = repository.findById(3);
        assertNotNull(eventCategory);
        assertEquals("Comedy",eventCategory.getCategoryName());
    }
    @Test
    void shouldFindByName() {
        EventCategory eventCategory = repository.findByName("Music");
        assertNotNull(eventCategory);
        assertEquals(1, eventCategory.getCategoryId());
    }
    @Test
    void shouldAddEventCategory (){

        EventCategory eventCategory = MakeEventCategory();
        EventCategory actual = repository.add(eventCategory);
        assertNotNull(actual);

    }


    EventCategory MakeEventCategory() {

        EventCategory eventCategory =

                new EventCategory(5,"TestCategory","Description Test");

        return eventCategory;

    }

//    @Test
//    void deleteTest(){
//
//        assertTrue(repository.deleteById(1));
//        assertFalse(repository.deleteById(1));
//        assertFalse(repository.deleteById(99));
//    }



}