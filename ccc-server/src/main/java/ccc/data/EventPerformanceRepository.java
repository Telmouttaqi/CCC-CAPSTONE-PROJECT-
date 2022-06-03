package ccc.data;

import ccc.models.EventPerformance;

import java.util.List;

public interface EventPerformanceRepository {
    List<EventPerformance> findAll();

    EventPerformance findById(int eventId, int performanceId);

    EventPerformance add(EventPerformance eventPerformance);
}
