package ccc.data;

import ccc.models.Rsvp;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RsvpRepository {
    List<Rsvp> findAll();

    Rsvp findById(String username, int eventId);

    Rsvp add(Rsvp rsvp);

    boolean update(Rsvp rsvp);




}
