package ccc.domain;

import ccc.data.EventCategoryRepository;
import ccc.data.EventCultureRepository;
import ccc.models.EventCategory;
import ccc.models.EventCulture;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest()
class EventCultureServiceTest {

    @Autowired
    EventCultureService service;

    @MockBean
    EventCultureRepository repository;

//    @Test
//    void shouldFindByEventCultureyId(){
//        EventCulture eventCulture = makeEventCulture();
//        Mockito.when(repository.findById(1)).thenReturn(eventCulture);
//        EventCulture result = service.findById(1);
//        assertEquals(eventCulture, result);
//    }


    @Test
    void shouldAdd(){
        EventCulture eventCulture = makeEventCulture();
        EventCulture newEventCulture = makeEventCulture();
        newEventCulture.setCultureId(0);

        Mockito.when(repository.add(newEventCulture)).thenReturn(eventCulture);

        Result<EventCulture> result = service.addEventCulture(newEventCulture);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(eventCulture, result.getPayload());
    }


    @Test
    void shouldUpdate(){
        EventCulture eventCulture = makeEventCulture();
        eventCulture.setCultureId(8);

        Mockito.when(repository.findById(8)).thenReturn(eventCulture);
        Mockito.when(repository.update(any())).thenReturn(true);

        Result<EventCulture> result = service.updateEventCulture(eventCulture);
        assertEquals(ResultType.SUCCESS, result.getType());
    }



    EventCulture makeEventCulture() {

        EventCulture eventCulture = new EventCulture(4,"CultureName","CountryFlag");
        return eventCulture;

    }


}