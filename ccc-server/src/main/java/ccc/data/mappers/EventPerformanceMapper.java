package ccc.data.mappers;

import ccc.models.EventPerformance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventPerformanceMapper implements RowMapper<EventPerformance> {

    @Override
    public EventPerformance mapRow(ResultSet rs, int rowNum) throws SQLException {
        EventPerformance eventPerformance = new EventPerformance();

        eventPerformance.setEventId(rs.getInt("event_id"));
        eventPerformance.setPerformanceId(rs.getInt("performance_id"));

        return eventPerformance;
    }
}
