package ccc.models;

import java.util.Objects;

public class EventCategory {

    private int categoryId;
    private String categoryName;
    private String categoryDescription;

    public EventCategory() {
    }

    public EventCategory(int eventCategoryId, String categoryName, String categoryDescription) {
        this.categoryId = eventCategoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventCategory)) return false;
        EventCategory that = (EventCategory) o;
        return getCategoryId() == that.getCategoryId() && Objects.equals(getCategoryName(), that.getCategoryName()) && Objects.equals(getCategoryDescription(), that.getCategoryDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategoryId(), getCategoryName(), getCategoryDescription());
    }
}
