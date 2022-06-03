package ccc.domain;


import ccc.data.EventCultureRepository;
import ccc.models.Event;
import ccc.models.EventCulture;
import ccc.models.Location;
import ccc.models.Performances;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCultureService {

    private final EventCultureRepository repository;

    public EventCultureService(EventCultureRepository repository) {
        this.repository = repository;
    }


    public List<EventCulture> findAll() {
        return repository.findAll();
    }

    public Result<EventCulture> findById(int cultureId) {

        Result result = new Result();
        EventCulture eventCulture = repository.findById(cultureId);
        if(eventCulture == null) {
            result.addMessage("Unable to find the event Culture",ResultType.NOT_FOUND);

        }

        result.setPayload(eventCulture);
        return result;
    }

    public EventCulture findByName(String cultureName) {
        return repository.findByName(cultureName);
    }

    public Result<EventCulture> addEventCulture(EventCulture eventCulture){
        Result<EventCulture> result = validate(eventCulture);
        if (!result.isSuccess()) {
            return result;
        }
        if (eventCulture.getCultureId() != 0) {
            result.addMessage("Event Culture ID cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        eventCulture = repository.add(eventCulture);
        result.setPayload(eventCulture);
        return result;
    }


    private Result<EventCulture> validate(EventCulture eventCulture){
        Result<EventCulture> result = new Result<>();

        if (eventCulture == null) {
            result.addMessage("Event Culture Cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(eventCulture.getCultureName())) {
            result.addMessage("Culture Name is required.", ResultType.INVALID);
        }

        return result;
    }

    public Result<EventCulture> updateEventCulture(EventCulture eventCulture){
        Result<EventCulture> result = new Result<>();

        result = validate(eventCulture);
        if (!result.isSuccess()) {
            return result;
        }


        if (eventCulture.getCultureId() <= 0) {
            result.addMessage("Event Culture ID must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(eventCulture)) {
            String msg = String.format("Event Culture ID with id %d is not found.", eventCulture.getCultureId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

//    public boolean deleteById(int cultureId) {
//        return repository.deleteById(cultureId);
//    }


}
