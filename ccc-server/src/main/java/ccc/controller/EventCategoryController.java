package ccc.controller;


import ccc.domain.EventCategoryService;
import ccc.domain.Result;
import ccc.models.EventCategory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventCategory")
public class EventCategoryController {

    private final EventCategoryService service;

    public EventCategoryController(EventCategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventCategory> findAll() {
        return service.findAll();
    }

    @GetMapping("/{eventCategoryId}")
    public ResponseEntity<Object> findByEventCategoryId(@PathVariable int eventCategoryId) {

        Result<EventCategory> result = service.findById(eventCategoryId);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }

    @PostMapping
    public ResponseEntity<Object> addEventCategory(@RequestBody EventCategory eventCategory) {
        Result<EventCategory> result = service.addEventCategory(eventCategory);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{eventCategoryId}")
    public ResponseEntity<Object> updateEventCategory(@PathVariable int eventCategoryId, @RequestBody EventCategory eventCategory){
        if (eventCategoryId != eventCategory.getCategoryId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<EventCategory> result = service.updateEventCategory(eventCategory);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

//    @DeleteMapping("/{eventCategoryId}")
//    public ResponseEntity<Void> deleteById(@PathVariable int eventCategoryId) {
//        if (service.deleteById(eventCategoryId)) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
