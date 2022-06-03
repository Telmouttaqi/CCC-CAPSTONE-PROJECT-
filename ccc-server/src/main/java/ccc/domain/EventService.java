package ccc.domain;

import ccc.data.EventRepository;
import ccc.models.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> findAll() {
        return repository.findAll();
    }

    public List<Event> findByUsername(String username){
        return repository.findByUsername(username);
    }

    public List<Event> findUnApprove(){return repository.findUnApprove();}

    public List<Event> findByDate(LocalDate date){return repository.findByDate(date);}

    public Result<Event> findByEventId(int eventId){
        Result<Event> result = new Result<>();

        Event event = repository.findByEventId(eventId);

        if (event == null) {
            String msg = String.format("Unable to find event with id %d.", eventId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        } else {
            result.setPayload(event);
        }

        return result;
    }

    public Result<Event> addEvent(Event event){
        Result<Event> result = validate(event);
        if (!result.isSuccess()) {
            return result;
        }

        if (event.getEventId() != 0) {
            result.addMessage("eventId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        event = repository.addEvent(event);
        result.setPayload(event);
        return result;
    }

    public Result<Event> updateEvent(Event event){
        Result<Event> result = new Result<>();
        String username = findByEventId(event.getEventId()).getPayload().getUsername();
        if( !username.equals(event.getUsername())){
            result.addMessage("UNAUTHORIZED!", ResultType.FORBIDDEN);
            return result;
        }

        result = validate(event);
        if (!result.isSuccess()) {
            return result;
        }


        if (event.getEventId() <= 0) {
            result.addMessage("eventId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(event)) {
            String msg = String.format("Event with id %d is not found.", event.getEventId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int eventId) {
        return repository.deleteById(eventId);
    }

    private Result<Event> validate(Event event){
        Result<Event> result = new Result<>();

        if (event == null) {
            result.addMessage("Event cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(event.getEventName())) {
            result.addMessage("An Event requires a name.", ResultType.INVALID);
        }

        if (event.getStartDate() == null) {
            result.addMessage("An Event requires a starting date.", ResultType.INVALID);
        }

        if (event.getStartDate() != null && event.getStartDate().isBefore(LocalDate.now())) {
            result.addMessage("An Event needs to happen in the future.", ResultType.INVALID);
        }

        if (event.getEndDate() == null) {
            event.setEndDate(event.getStartDate());
        }
        if (event.getStartDate() != null && event.getEndDate().isBefore(event.getStartDate())) {
            result.addMessage("An Event's end date can't before start date.", ResultType.INVALID);
        }

        if (event.getCapacity() <= 1) {
            result.addMessage("An Event requires greater capacity.", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(event.getCategory())) {
            result.addMessage("An Event requires a category type.", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(event.getCulture())) {
            result.addMessage("Events need a culture.", ResultType.INVALID);
        }


        if (Validations.isNullOrBlank(event.getUsername())) {
            result.addMessage("An Event requires a host.", ResultType.INVALID);
        }

        return result;
    }
}
