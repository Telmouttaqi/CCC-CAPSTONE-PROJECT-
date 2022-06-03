package ccc.data;

import ccc.data.mappers.RsvpMapper;
import ccc.models.Rsvp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class RsvpJdbcTemplateRepository implements RsvpRepository {

    private final JdbcTemplate jdbcTemplate;

    public RsvpJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Rsvp> findAll() {
        final String sql = "select username, event_id, approved "
                + "from rsvp ;";
        return jdbcTemplate.query(sql, new RsvpMapper());

    }

    @Override
    public Rsvp findById(String username,int eventId) {
        final String sql = "select username, event_id, approved "
                + " from rsvp "
                + " where username = ?"
                + " and "
                + " event_id = ?;";
        Rsvp rsvp = jdbcTemplate.query(sql, new RsvpMapper(), username,eventId).stream()
                .findFirst().orElse(null);

        return rsvp;
    }

    @Override
    public Rsvp add(Rsvp rsvp) {

        final String sql = "insert into rsvp (username, event_id, approved) "
                + " values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,rsvp.getUsername());
            ps.setInt(2,rsvp.getEventId());
            ps.setBoolean(3,rsvp.isApproved());

            return ps;

        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        return rsvp;
    }



    @Override
    public boolean update(Rsvp rsvp) {
        final String sql = "update rsvp set "
                + " approved = ? "
                + " where username = ? and event_id =? ;";


       return jdbcTemplate.update(sql,
               rsvp.isApproved(),
               rsvp.getUsername(),
               rsvp.getEventId()) > 0;
    }

}

