package ccc.data;

import ccc.data.mappers.EventPerformanceMapper;
import ccc.models.EventPerformance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EventPerformanceJdbcTemplateRepository implements EventPerformanceRepository {

    private final JdbcTemplate jdbcTemplate;


    public EventPerformanceJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EventPerformance> findAll() {
        final String sql = "select event_id, performance_id "
                + "from event_performance ;";
        return jdbcTemplate.query(sql, new EventPerformanceMapper());

    }

    @Override
    public EventPerformance findById(int eventId, int performanceId) {
        final String sql = "select event_id, performance_id "
                + "from event_performance "
                + "where event_id = ? and performance_id = ?;";
        EventPerformance eventPerformance = jdbcTemplate.query(sql, new EventPerformanceMapper(), eventId, performanceId).stream()
                .findFirst().orElse(null);

        return eventPerformance;
    }

    @Override
    public EventPerformance add(EventPerformance eventPerformance) {

        final String sql = "insert into event_performance (event_id, performance_id) "
                + " values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,eventPerformance.getEventId());
            ps.setInt(2,eventPerformance.getPerformanceId());

            return ps;

        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }
        return eventPerformance;
    }
}
