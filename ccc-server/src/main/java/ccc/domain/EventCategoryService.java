package ccc.domain;


import ccc.data.EventCategoryRepository;
import ccc.models.EventCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventCategoryService {

    private final EventCategoryRepository repository;


    public List<EventCategory> findAll() {
        return repository.findAll();
    }

    public EventCategoryService(EventCategoryRepository repository) {
        this.repository = repository;
    }

    public Result<EventCategory> findById(int categoryId) {

        Result result = new Result();
        EventCategory eventCategory =  repository.findById(categoryId);
        if(eventCategory == null) {
            result.addMessage("Unable to find Category Event",ResultType.NOT_FOUND);
        }
        result.setPayload(eventCategory);
        return result;
    }


    public Result<EventCategory> addEventCategory(EventCategory eventCategory){

        Result<EventCategory> result = validate(eventCategory);
        if (!result.isSuccess()) {
            return result;
        }

        if (eventCategory.getCategoryId() != 0) {
            result.addMessage("Event Category ID cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        eventCategory = repository.add(eventCategory);
        result.setPayload(eventCategory);
        return result;
    }


    private Result<EventCategory> validate(EventCategory eventCategory){
        Result<EventCategory> result = new Result<>();

        if (eventCategory == null) {
            result.addMessage("Culture Category Cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(eventCategory.getCategoryName())) {
            result.addMessage("Culture Category Name is required.", ResultType.INVALID);
        }
        return result;
    }

    public Result<EventCategory> updateEventCategory(EventCategory eventCategory){
        Result<EventCategory> result = new Result<>();

        result = validate(eventCategory);
        if (!result.isSuccess()) {
            return result;
        }


        if (eventCategory.getCategoryId() <= 0) {
            result.addMessage("Event Category ID must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(eventCategory)) {
            String msg = String.format("Event Category ID with id %d is not found.", eventCategory.getCategoryId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

//    public boolean deleteById(int eventCategoryId) {
//        return repository.deleteById(eventCategoryId);
//    }
}
