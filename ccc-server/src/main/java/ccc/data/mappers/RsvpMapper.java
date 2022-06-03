package ccc.data.mappers;

import ccc.models.Rsvp;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RsvpMapper implements RowMapper<Rsvp> {

    @Override
    public Rsvp mapRow(ResultSet rs, int rowNum) throws SQLException {

        Rsvp rsvp = new Rsvp();

        rsvp.setUsername(rs.getString("username"));
        rsvp.setEventId(rs.getInt("event_id"));
        rsvp.setApproved(rs.getBoolean("approved"));
        return rsvp;
    }

}

