package ccc.models;

import java.time.LocalDate;

public class Event {

//    event_id int primary key auto_increment,
//    event_name varchar(100) not null,
//    location_id int null,
//    start_date date not null,
//    end_date date null,
//    capacity int null,
//    category_id int null,
//    country_id int null,
//    user_id int not null,

    private int eventId;
    private String eventName;
    private String fullAddress;
    private LocalDate startDate;
    private LocalDate endDate;
    private int capacity;
    private String category;
    private String culture;
    private String username;
    private boolean adminApprove;

    public Event() {
    }

    public Event(int eventId, String eventName, String fullAddress, LocalDate startDate, LocalDate endDate, int capacity, String category, String culture, String username) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.fullAddress = fullAddress;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.category = category;
        this.culture = culture;
        this.username = username;
    }

    public int getEventId() {
        return eventId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public boolean isAdminApprove() {
        return adminApprove;
    }

    public void setAdminApprove(boolean adminApprove) {
        this.adminApprove = adminApprove;
    }
}
