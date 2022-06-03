package ccc.controller;


import ccc.domain.PerformancesService;
import ccc.domain.Result;
import ccc.models.Event;
import ccc.models.Performances;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformancesController {

    private final PerformancesService performancesService;

    public PerformancesController(PerformancesService performancesService) {
        this.performancesService = performancesService;
    }

    @GetMapping
    public List<Performances> findAll() {
        return performancesService.findAll();
    }

    @GetMapping("/{performanceId}")
    public ResponseEntity<Object> findById(@PathVariable int performanceId) {
        Performances performances = performancesService.findById(performanceId);
        if (performances == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(performances);
    }

    @PostMapping
    public ResponseEntity<Object> addPerformance(@RequestBody Performances performances) {
        Result<Performances> result = performancesService.addPerformance(performances);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{performanceId}")
    public ResponseEntity<Object> update(@PathVariable int performanceId, @RequestBody Performances performances) {
        if (performanceId != performances.getPerformancesId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Performances> result = performancesService.updatePerformance(performances);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{performanceId}")
    public ResponseEntity<Void> deleteById(@PathVariable int performanceId) {
        if (performancesService.deleteById(performanceId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
