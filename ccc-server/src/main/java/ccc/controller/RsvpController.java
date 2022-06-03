
package ccc.controller;

import ccc.domain.Result;
import ccc.domain.RsvpService;
import ccc.models.Rsvp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rsvp")
public class RsvpController {
    private final RsvpService service;

    public RsvpController(RsvpService service) {
        this.service = service;
    }



    @GetMapping
    public List<Rsvp> findAll() {
        return service.findAll();
    }


    @GetMapping("/{userId}/{eventId}")
    public ResponseEntity<Object> findByUserId(@PathVariable String username, @PathVariable int eventId) {

        Result<Rsvp> result = service.findById(username, eventId);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }


    @PostMapping
    public ResponseEntity<Object> addRsvp(@RequestBody Rsvp rsvp) {
        Result<Rsvp> result = service.addRsvp(rsvp);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{userId}/{eventId}")
    public ResponseEntity<Object> updateEvent(@PathVariable String username,@PathVariable int eventId, @RequestBody Rsvp rsvp){
        if (username.equals(rsvp.getUsername()) || eventId != rsvp.getEventId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Rsvp> result = service.updateRsvp(rsvp);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }
}

