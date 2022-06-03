package ccc.models;

import java.util.Objects;

public class Performances {

    private int performancesId;
    private String name;
    private String description;

    public Performances() {
    }

    public Performances(int performancesId, String name, String description) {
        this.performancesId = performancesId;
        this.name = name;
        this.description = description;
    }

    public int getPerformancesId() {
        return performancesId;
    }

    public void setPerformancesId(int performancesId) {
        this.performancesId = performancesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Performances)) return false;
        Performances that = (Performances) o;
        return getPerformancesId() == that.getPerformancesId() && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPerformancesId(), getName(), getDescription());
    }
}
