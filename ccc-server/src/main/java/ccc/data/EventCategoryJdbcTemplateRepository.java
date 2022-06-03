package ccc.data;

import ccc.data.mappers.EventCategoryMapper;
import ccc.models.EventCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EventCategoryJdbcTemplateRepository implements EventCategoryRepository{
    private final JdbcTemplate jdbcTemplate;

    public EventCategoryJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<EventCategory> findAll() {
        final String sql = "select "
                + "category_id, category_name, category_description "
                + " from event_category "
                + " limit 1000;";
        return jdbcTemplate.query(sql, new EventCategoryMapper());
    }

    @Override
    public EventCategory add(EventCategory eventCategory) {
        final String sql = "insert into event_category (category_name, category_description) "
                + " values (?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, eventCategory.getCategoryName());
            ps.setString(2, eventCategory.getCategoryDescription());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        eventCategory.setCategoryId(keyHolder.getKey().intValue());
        return eventCategory;
    }

    @Override
    public EventCategory findById(int categoryId) {
        final String sql = "select category_id, category_name, category_description "
                + " from event_category "
                + " where category_id = ?;";

        EventCategory eventCategory = jdbcTemplate.query(sql, new EventCategoryMapper(), categoryId).stream()
                .findFirst().orElse(null);

        return eventCategory;

    }

    @Override
    public EventCategory findByName(String categoryName) {
        final String sql = "select category_id, category_name, category_description "
                + " from event_category "
                + " where category_name = ?;";
        EventCategory eventCategory = jdbcTemplate.query(sql, new EventCategoryMapper(), categoryName).stream()
                .findFirst().orElse(null);

        return eventCategory;
    }

    @Override
    public Boolean update(EventCategory eventCategory) {
        final String sql = "update event_category set "
                + " category_name = ?, "
                + " category_description = ? "
                + " where category_id = ?;";

        return jdbcTemplate.update(sql,
                eventCategory.getCategoryName(),
                eventCategory.getCategoryDescription(),
                eventCategory.getCategoryId()) > 0;

    }

//    @Transactional
//    @Override
//    public Boolean deleteById(int categoryId) {
//        jdbcTemplate.update("delete from event_table where category_id = ?;", categoryId);
//        return jdbcTemplate.update("delete from event_category where category_id = ?;", categoryId) > 0;
//    }
}
