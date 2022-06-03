package ccc.domain;


import ccc.data.RsvpRepository;
import ccc.models.Event;
import ccc.models.Rsvp;
import ccc.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RsvpService {

    private final RsvpRepository repository;

    public RsvpService(RsvpRepository repository) {
        this.repository = repository;
    }

    public List<Rsvp> findAll() {
        return repository.findAll();
    }

    public Result<Rsvp> findById(String username,int eventId) {

        Result<Rsvp> result = new Result<>();

        Rsvp rsvp = repository.findById(username, eventId);

        if (rsvp == null) {
            String msg = String.format("Unable to find rsvp.", eventId);
            result.addMessage(msg, ResultType.NOT_FOUND);
        } else {
            result.setPayload(rsvp);
        }

        return result;
    }


    public Result<Rsvp> addRsvp(Rsvp rsvp){
        Result<Rsvp> result = validate(rsvp);
        if (!result.isSuccess()) {
            return result;
        }

        rsvp = repository.add(rsvp);
        result.setPayload(rsvp);
        return result;
    }

    private Result<Rsvp> validate(Rsvp rsvp){
        Result<Rsvp> result = new Result<>();

        if (rsvp == null) {
            result.addMessage("RSVP Cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(rsvp.getUsername())) {
            result.addMessage("RSVP need a USER. ", ResultType.INVALID);
            return result;
        }

        return result;
    }

    public Result<Rsvp> updateRsvp(Rsvp rsvp){
        Result<Rsvp> result = new Result<>();

        result = validate(rsvp);
        if (!result.isSuccess()) {
            return result;
        }

        if (!repository.update(rsvp)) {

            result.addMessage( "RSVP NOT FOUND!", ResultType.NOT_FOUND);
        }

        return result;
    }





}
