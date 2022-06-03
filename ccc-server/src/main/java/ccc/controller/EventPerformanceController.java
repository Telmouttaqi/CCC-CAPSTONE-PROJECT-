package ccc.controller;

import ccc.domain.EventPerformanceService;
import ccc.domain.Result;
import ccc.models.EventPerformance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event/performance")
public class EventPerformanceController {
    private final EventPerformanceService service;

    public EventPerformanceController(EventPerformanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<EventPerformance> findAll() {
        return service.findAll();
    }

    @GetMapping("/{eventId}/{performanceId}")
    public EventPerformance findById(@PathVariable int eventId,@PathVariable int performanceId) {
        return service.findById(eventId, performanceId);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody EventPerformance eventPerformance) {
        Result<EventPerformance> result = service.add(eventPerformance);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }
}
