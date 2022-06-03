package ccc.data.mappers;

import ccc.models.EventCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventCategoryMapper implements RowMapper<EventCategory> {

    @Override
    public EventCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventCategory eventCategory = new EventCategory();

        eventCategory.setCategoryId(rs.getInt("category_id"));
        eventCategory.setCategoryName(rs.getString("category_name"));
        eventCategory.setCategoryDescription(rs.getString("category_description"));

        return eventCategory;
    }
}
