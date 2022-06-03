package ccc.data;

import ccc.models.Event;
import ccc.models.EventCulture;

import java.util.List;

public interface EventCultureRepository {

    List<EventCulture> findAll();

    EventCulture findById(int cultureId);

    EventCulture add(EventCulture eventCulture);

    EventCulture findByName(String cultureName);

    Boolean update(EventCulture eventCulture);

   // Boolean deleteById(int cultureId);
}
