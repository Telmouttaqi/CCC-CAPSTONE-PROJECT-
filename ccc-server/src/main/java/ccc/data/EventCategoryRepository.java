package ccc.data;

import ccc.models.Event;
import ccc.models.EventCategory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventCategoryRepository {

    List<EventCategory> findAll();

    EventCategory add(EventCategory eventCategory);

    EventCategory findById(int categoryId);

    EventCategory findByName(String categoryName);

    Boolean update(EventCategory eventCategory);

    //Boolean deleteById(int categoryId);


}
