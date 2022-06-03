package ccc.data;

import ccc.models.Event;
import ccc.models.Rsvp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RsvpJdbcTemplateRepositoryTest {

    @Autowired
    RsvpJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Rsvp> rsvps = repository.findAll();
        assertNotNull(rsvps);
        assertTrue(rsvps.size()>=3);
    }

    @Test
    void ShouldFindByUserId() {
        Rsvp rsvp = repository.findById("john@smith.com",1);
        assertNotNull(rsvp);

    }

    @Test
    void shouldUpdateRSVP(){
        Rsvp rsvp = makeRSVP();
        rsvp.setApproved(true);
        assertFalse(repository.update(rsvp));
    }
    @Test
    void shouldAdd(){
        Rsvp rsvp = new Rsvp("john@smith.com",10,true);

        Rsvp returned = repository.add(rsvp);

        assertEquals("john@smith.com", rsvp.getUsername());
        assertEquals(10, rsvp.getEventId());

    }
    Rsvp makeRSVP(){

        Rsvp rsvp = new Rsvp("john@smith.com",5,false);
        return rsvp;

    }




}