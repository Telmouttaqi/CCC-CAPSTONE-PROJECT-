package ccc.data;

import ccc.models.Event;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository {
    List<Event> findAll();

    List<Event> findByUsername(String username);

    @Transactional
    Event findByEventId(int eventId);

    List<Event> findByDate(LocalDate date);

    List<Event> findUnApprove();

    Event addEvent(Event event);

    boolean update(Event event);

    @Transactional
    boolean deleteById(int eventId);
}
