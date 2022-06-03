package ccc.models;

import java.util.Objects;

public class Rsvp {

    private String username;
    private int eventId;
    private boolean approved;

    public Rsvp() {
    }

    public Rsvp(String username, int eventId, boolean approved) {
        this.username = username;
        this.eventId = eventId;
        this.approved = approved;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
