package ccc.domain;

import ccc.data.EventCategoryRepository;
import ccc.models.EventCategory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class EventCategoryServiceTest {

    @Autowired
    EventCategoryService service;

    @MockBean
    EventCategoryRepository repository;

//    @Test
//    void shouldFindByEventCategoryId(){
//        EventCategory eventCategory = MakeEventCategory();
//
//        Mockito.when(repository.findById(1)).thenReturn(eventCategory);
//        EventCategory result = service.findById(1);
//        assertEquals(eventCategory, result);
//    }


    @Test
    void shouldAdd(){
        EventCategory eventCategory = MakeEventCategory();
        EventCategory newEventCategory = MakeEventCategory();
        newEventCategory.setCategoryId(0);

        Mockito.when(repository.add(newEventCategory)).thenReturn(eventCategory);

        Result<EventCategory> result = service.addEventCategory(newEventCategory);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(eventCategory, result.getPayload());
    }


    @Test
    void shouldUpdate(){
        EventCategory eventCategory = MakeEventCategory();
        eventCategory.setCategoryId(8);

        Mockito.when(repository.findById(8)).thenReturn(eventCategory);
        Mockito.when(repository.update(any())).thenReturn(true);

        Result<EventCategory> result = service.updateEventCategory(eventCategory);
        assertEquals(ResultType.SUCCESS, result.getType());
    }



    EventCategory MakeEventCategory() {
        EventCategory eventCategory =
                new EventCategory(5,"TestCategory","Description Test");
        return eventCategory;

    }


}