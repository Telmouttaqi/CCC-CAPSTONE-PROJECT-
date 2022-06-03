package ccc.models;

import java.util.Objects;

public class EventCulture {

    private int cultureId;
    private String cultureName;
    // image url !
    private String countryFlag;

    public EventCulture() {
    }

    public EventCulture(int cultureId, String cultureName, String countryFlag) {
        this.cultureId = cultureId;
        this.cultureName = cultureName;
        this.countryFlag = countryFlag;
    }

    public int getCultureId() {
        return cultureId;
    }

    public void setCultureId(int cultureId) {
        this.cultureId = cultureId;
    }

    public String getCultureName() {
        return cultureName;
    }

    public void setCultureName(String cultureName) {
        this.cultureName = cultureName;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventCulture)) return false;
        EventCulture that = (EventCulture) o;
        return getCultureId() == that.getCultureId() && Objects.equals(getCultureName(), that.getCultureName()) && Objects.equals(getCountryFlag(), that.getCountryFlag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCultureId(), getCultureName(), getCountryFlag());
    }

}
