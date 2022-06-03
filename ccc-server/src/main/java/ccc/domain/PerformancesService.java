package ccc.domain;


import ccc.data.PerformancesRepository;

import ccc.models.Performances;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PerformancesService {

    private final PerformancesRepository repository;

    public PerformancesService(PerformancesRepository repository) {
        this.repository = repository;
    }

    public List<Performances> findAll() {
        return repository.findAll();
    }

    public Performances findById(int performanceId) {
        return repository.findById(performanceId);
    }

    public Result<Performances> addPerformance(Performances performance){
        Result<Performances> result = validate(performance);
        if (!result.isSuccess()) {
            return result;
        }

        if (performance.getPerformancesId() != 0) {
            result.addMessage("Performance cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        performance = repository.addPerformance(performance);
        result.setPayload(performance);
        return result;
    }

    public Result<Performances> updatePerformance(Performances performance){
        Result<Performances> result = new Result<>();

        result = validate(performance);
        if (!result.isSuccess()) {
            return result;
        }


        if (performance.getPerformancesId() <= 0) {
            result.addMessage("Performance ID must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(performance)) {
            String msg = String.format("Performance ID with id %d is not found.", performance.getPerformancesId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    private Result<Performances> validate(Performances performance){
        Result<Performances> result = new Result<>();

        if (performance == null) {
            result.addMessage("Performance cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(performance.getName())) {
            result.addMessage("Performance need a name.", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(performance.getDescription())) {
            result.addMessage("Performance need a Description.", ResultType.INVALID);
        }

        return result;
    }


    public boolean deleteById(int performanceId) {
        return repository.deleteById(performanceId);
    }


}
