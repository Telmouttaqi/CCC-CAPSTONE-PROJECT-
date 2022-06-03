package ccc.domain;

import ccc.data.EventCategoryRepository;
import ccc.data.RsvpRepository;
import ccc.models.Event;
import ccc.models.Location;
import ccc.models.Rsvp;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class RsvpServiceTest {

    @Autowired
    RsvpService service;

    @MockBean
    RsvpRepository repository;



    @Test
    void shouldAdd(){

        Rsvp rsvp = makeRSVP();
        Rsvp newRsvp = makeRSVP();
        newRsvp.setEventId(0);

        Mockito.when(repository.add(newRsvp)).thenReturn(rsvp);
        Result<Rsvp> result = service.addRsvp(newRsvp);

        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(rsvp, result.getPayload());
    }

    @Test
    void ShouldUpdate()
    {
        Rsvp rsvp = null;
        Result<Rsvp> result = service.updateRsvp(rsvp);
        assertEquals(ResultType.INVALID,result.getType());
    }

    Rsvp makeRSVP(){
        Rsvp rsvp = new Rsvp("john@smith.com",1,true);
        return rsvp;
    }

}