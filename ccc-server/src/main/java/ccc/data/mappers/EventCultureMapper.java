package ccc.data.mappers;

import ccc.models.EventCulture;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventCultureMapper implements RowMapper<EventCulture> {

    @Override
    public EventCulture mapRow(ResultSet rs, int rowNum) throws SQLException {

        EventCulture eventCulture = new EventCulture();

        eventCulture.setCultureId(rs.getInt("culture_id"));
        eventCulture.setCultureName(rs.getString("culture_name"));
        eventCulture.setCountryFlag(rs.getString("country_flag"));


        return eventCulture;
    }
}
