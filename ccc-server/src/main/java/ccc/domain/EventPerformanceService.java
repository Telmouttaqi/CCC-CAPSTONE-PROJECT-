package ccc.domain;

import ccc.data.EventPerformanceRepository;
import ccc.models.Event;
import ccc.models.EventPerformance;
import ccc.models.Rsvp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventPerformanceService {
    private final EventPerformanceRepository repository;


    public EventPerformanceService(EventPerformanceRepository repository) {
        this.repository = repository;
    }

    public List<EventPerformance> findAll(){return repository.findAll();};

    public EventPerformance findById(int eventId, int performanceId){return repository.findById(eventId, performanceId);}

    public Result<EventPerformance> add(EventPerformance eventPerformance){
        Result<EventPerformance> result = validate(eventPerformance);
        if (!result.isSuccess()) {
            return result;
        }
        eventPerformance = repository.add(eventPerformance);
        result.setPayload(eventPerformance);
        return result;
    }

    private Result<EventPerformance> validate(EventPerformance eventPerformance){
        Result<EventPerformance> result = new Result<>();

        if (eventPerformance == null) {
            result.addMessage("Event Performance cannot be null", ResultType.INVALID);
            return result;
        }

        if(eventPerformance.getEventId() == 0){
            result.addMessage("Invalid event.", ResultType.INVALID);
        }

        if(eventPerformance.getPerformanceId() == 0){
            result.addMessage("Invalid performance.", ResultType.INVALID);
        }

        return result;
    }
}
