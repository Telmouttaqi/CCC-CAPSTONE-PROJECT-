package ccc.data.mappers;

import ccc.models.Event;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setFullAddress(rs.getString("full_address"));
        event.setStartDate(rs.getDate("start_date").toLocalDate());
        if (rs.getDate("end_date") != null) {
            event.setEndDate(rs.getDate("end_date").toLocalDate());
        } else {
            event.setEndDate(rs.getDate("start_date").toLocalDate());
        }
        event.setCapacity(rs.getInt("capacity"));
        event.setCategory(rs.getString("category_name"));
        event.setCulture(rs.getString("culture_name"));
        event.setUsername(rs.getString("username"));

        return event;
    }
}