package ccc.models;

import java.util.Objects;

public class Location {

    private int locationId;
    private String address;
    private String city;
    private String state;
    private String zipCode;

    public Location() {
    }

    public Location(int locationId, String address, String city, String state, String zipCode) {
        this.locationId = locationId;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return getLocationId() == location.getLocationId() && Objects.equals(getAddress(), location.getAddress()) && Objects.equals(getCity(), location.getCity()) && Objects.equals(getState(), location.getState()) && Objects.equals(getZipCode(), location.getZipCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocationId(), getAddress(), getCity(), getState(), getZipCode());
    }
}
