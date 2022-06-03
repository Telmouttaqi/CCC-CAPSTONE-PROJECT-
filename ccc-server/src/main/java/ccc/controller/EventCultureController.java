package ccc.controller;


import ccc.domain.EventCultureService;
import ccc.domain.Result;
import ccc.models.EventCulture;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventCulture")
public class EventCultureController {

    private final EventCultureService service;


    public EventCultureController(EventCultureService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventCulture> findAll() {
        return service.findAll();
    }

    @GetMapping("/{eventCultureId}")
    public ResponseEntity<Object> findByEventCultureId(@PathVariable int eventCultureId) {
        Result<EventCulture> result = service.findById(eventCultureId);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping
    public ResponseEntity<Object> addEventCulture(@RequestBody EventCulture eventCulture) {
        Result<EventCulture> result = service.addEventCulture(eventCulture);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{eventCultureId}")
    public ResponseEntity<Object> updateEventCulture(@PathVariable int eventCultureId, @RequestBody EventCulture eventCulture){
        if (eventCultureId != eventCulture.getCultureId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<EventCulture> result = service.updateEventCulture(eventCulture);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

//    @DeleteMapping("/{eventCultureId}")
//    public ResponseEntity<Void> deleteById(@PathVariable int eventCultureId) {
//        if (service.deleteById(eventCultureId)) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
