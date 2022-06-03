package ccc.models;

public class EventPerformance {
//    event_id int not null,
//    performance_id int not null,

    private int eventId;
    private int performanceId;

    public EventPerformance() {
    }

    public EventPerformance(int eventId, int performanceId) {
        this.eventId = eventId;
        this.performanceId = performanceId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(int performanceId) {
        this.performanceId = performanceId;
    }
}
