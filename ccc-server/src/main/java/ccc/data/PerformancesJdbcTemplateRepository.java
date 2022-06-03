package ccc.data;

import ccc.data.mappers.PerformancesMapper;
import ccc.models.Performances;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
public class PerformancesJdbcTemplateRepository implements PerformancesRepository {

    private final JdbcTemplate jdbcTemplate;

    public PerformancesJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Performances> findAll() {
        final String sql = "select performance_id, name, description from performances ;";
        return jdbcTemplate.query(sql, new PerformancesMapper());
    }

    @Override
    public Performances findById(int performanceId) {
        final String sql = "select performance_id, name, description"
                + " from performances "
                + " where performance_id = ?;";
        Performances result = jdbcTemplate.query(sql, new PerformancesMapper(), performanceId).stream()
                .findAny().orElse(null);
        return result;
    }



    @Override
    public Performances addPerformance(Performances performance) {
        final String sql = "insert into performances (name, description) values (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,performance.getName());
            ps.setString(2,performance.getDescription());
            return ps;

        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        performance.setPerformancesId(keyHolder.getKey().intValue());
        return performance;
    }

    @Override
    public Boolean update(Performances performance) {
        final String sql = "update performances set "
                + " name = ?, "
                + " description = ?"
                + " where performance_id = ?;";

        return jdbcTemplate.update(sql,
                performance.getName(),
                performance.getDescription(),
                performance.getPerformancesId()) > 0;
    }

    @Override
    @Transactional
    public Boolean deleteById(int performanceId) {
        jdbcTemplate.update("delete from event_performance where performance_id = ?;", performanceId);
        return jdbcTemplate.update("delete from performances where performance_id = ?;", performanceId) > 0;
    }

}
