package ccc.data;

import ccc.data.mappers.EventCultureMapper;
import ccc.data.mappers.EventMapper;
import ccc.models.EventCulture;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EventCultureJdbcTemplateRepository implements EventCultureRepository{

    private final JdbcTemplate jdbcTemplate;

    public EventCultureJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EventCulture> findAll() {
        final String sql = "select "
                + " culture_id, culture_name, country_flag "
                + " from event_culture "
                + " limit 1000;";
        return jdbcTemplate.query(sql, new EventCultureMapper());
    }

    @Override
    public EventCulture findById(int cultureId) {
        final String sql = "select culture_id, culture_name, country_flag "

                + " from event_culture "
                + " where culture_id = ?;";

        EventCulture eventCulture = jdbcTemplate.query(sql, new EventCultureMapper(), cultureId).stream()
                .findFirst().orElse(null);

        return eventCulture;
    }

    @Override
    public EventCulture add(EventCulture eventCulture) {
        final String sql = "insert into event_culture (culture_name, country_flag) "
                + " values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, eventCulture.getCultureName());
            ps.setString(2, eventCulture.getCountryFlag());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        eventCulture.setCultureId(keyHolder.getKey().intValue());
        return eventCulture;
    }

    @Override
    public EventCulture findByName(String cultureName) {
        final String sql = "select culture_id, culture_name, country_flag  "
                + " from event_culture "
                + " where culture_name = ?;";
        EventCulture eventCulture = jdbcTemplate.query(sql, new EventCultureMapper(), cultureName).stream()
                .findFirst().orElse(null);

        return eventCulture;
    }

    @Override
    public Boolean update(EventCulture eventCulture) {
        final String sql = "update event_culture set "
                + " culture_name = ?, "
                + " country_flag = ? "
                + " where culture_id = ?;";

        return jdbcTemplate.update(sql,
                eventCulture.getCultureName(),
                eventCulture.getCountryFlag(),
                eventCulture.getCultureId()) > 0 ;

    }

//    @Override
//    @Transactional
//    public Boolean deleteById(int cultureId) {
//        jdbcTemplate.update("delete from event_table where culture_id = ?;", cultureId);
//        return jdbcTemplate.update("delete from event_culture where culture_id = ?;", cultureId) > 0;
//    }

}

