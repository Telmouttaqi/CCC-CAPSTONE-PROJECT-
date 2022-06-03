package ccc.controller;

import ccc.domain.EventService;
import ccc.domain.Result;
import ccc.models.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping
    public List<Event> findAll() {
        return service.findAll();
    }


    @GetMapping("/unapproved")
    public List<Event> findUnApprove() {
        return service.findUnApprove();
    }


    @GetMapping("/user/{username}")
    public List<Event> findByUserId(@PathVariable String username) {
        return service.findByUsername(username);
    }

    @GetMapping("/date")
    public List<Event> findByDate(@RequestBody String date) {
        return service.findByDate(LocalDate.parse(date));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Object> findByEventId(@PathVariable int eventId) {
        Result<Event> result = service.findByEventId(eventId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping
    public ResponseEntity<Object> addEvent(@RequestBody Event event) {
        Result<Event> result = service.addEvent(event);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Object> updateEvent(@PathVariable int eventId, @RequestBody Event event){
        if (eventId != event.getEventId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Event> result = service.updateEvent(event);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteById(@PathVariable int eventId) {
        if (service.deleteById(eventId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
