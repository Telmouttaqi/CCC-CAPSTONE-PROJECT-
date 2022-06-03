package ccc.data;

import ccc.data.mappers.EventMapper;
import ccc.domain.Validations;
import ccc.models.Event;
import ccc.models.EventCategory;
import ccc.models.EventCulture;
import ccc.models.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Repository
public class EventJdbcTemplateRepository implements EventRepository {

    private final JdbcTemplate jdbcTemplate;
    private final LocationRepository locationRepo;
    private final EventCategoryRepository catRepo;
    private final EventCultureRepository culRepo;


    public EventJdbcTemplateRepository(JdbcTemplate jdbcTemplate, LocationRepository locationRepo, EventCategoryRepository catRepo, EventCultureRepository culRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.locationRepo = locationRepo;
        this.catRepo = catRepo;
        this.culRepo = culRepo;
    }

    @Override
    public List<Event> findAll() {
        final String sql = "select e.event_id, e.event_name, e.start_date, e.end_date, e.capacity, cat.category_name, cul.culture_name, e.username, "
                + "concat(l.address, ', ', l.city, ', ',  l.state, ', ',  l.zip_code) as full_address "
                + "from event_table e "
                + "left join location l on  l.location_id = e.location_id "
                + "left join event_category cat on cat.category_id = e.category_id "
                + "left join event_culture cul on cul.culture_id = e.culture_id "
                + "limit 1000;";
        return jdbcTemplate.query(sql, new EventMapper());
    }

    @Override
    public List<Event> findByUsername(String username) {
        final String sql = "select e.event_id, e.event_name, e.start_date, e.end_date, e.capacity, cat.category_name, cul.culture_name, e.username, "
                + "concat(l.address, ', ', l.city, ', ',  l.state, ', ',  l.zip_code) as full_address "
                + "from event_table e "
                + "left join location l on  l.location_id = e.location_id "
                + "left join event_category cat on cat.category_id = e.category_id "
                + "left join event_culture cul on cul.culture_id = e.culture_id "
                + "where e.username = ? "
                + "limit 1000;";

        return jdbcTemplate.query(sql, new EventMapper(), username);
    }

    @Override
    public Event findByEventId(int eventId) {

        final String sql = "select e.event_id, e.event_name, e.start_date, e.end_date, e.capacity, cat.category_name, cul.culture_name, e.username, "
                + "concat(l.address, ', ', l.city, ', ',  l.state, ', ',  l.zip_code) as full_address "
                + "from event_table e "
                + "left join location l on  l.location_id = e.location_id "
                + "left join event_category cat on cat.category_id = e.category_id "
                + "left join event_culture cul on cul.culture_id = e.culture_id "
                + "where e.event_id = ?;";

        Event event = jdbcTemplate.query(sql, new EventMapper(), eventId).stream()
                .findFirst().orElse(null);

        return event;
    }

    @Override
    public List<Event> findByDate(LocalDate date) {
        final String sql = "select e.event_id, e.event_name, e.start_date, e.end_date, e.capacity, cat.category_name, cul.culture_name, e.username, "
                + "concat(l.address, ', ', l.city, ', ',  l.state, ', ',  l.zip_code) as full_address "
                + "from event_table e "
                + "left join location l on  l.location_id = e.location_id "
                + "left join event_category cat on cat.category_id = e.category_id "
                + "left join event_culture cul on cul.culture_id = e.culture_id "
                + "where ? between e.start_date and e.end_date "
                + "limit 1000;";

        return jdbcTemplate.query(sql, new EventMapper(), date);
    }

    @Override
    public List<Event> findUnApprove() {
        final String sql = "select e.event_id, e.event_name, e.start_date, e.end_date, e.capacity, cat.category_name, cul.culture_name, e.username, "
                + "concat(l.address, ', ', l.city, ', ',  l.state, ', ',  l.zip_code) as full_address "
                + "from event_table e "
                + "left join location l on  l.location_id = e.location_id "
                + "left join event_category cat on cat.category_id = e.category_id "
                + "left join event_culture cul on cul.culture_id = e.culture_id "
                + "where e.admin_approve = false "
                + "limit 1000;";

        return jdbcTemplate.query(sql, new EventMapper());
    }

    @Override
    public Event addEvent(Event event) {
        final String eventSql = "insert into event_table (event_id, event_name, location_id, start_date, end_date, capacity, category_id, culture_id, username) "
                + " values (?,?,?,?,?,?,?,?,?);";

        Location location = findAndAddLocation(event.getFullAddress());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(eventSql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, event.getEventId());
            ps.setString(2, event.getEventName());
            ps.setInt(3, location == null ? null : location.getLocationId());
            ps.setDate(4, event.getStartDate() == null ? null : Date.valueOf(event.getStartDate()));
            ps.setDate(5, event.getEndDate() == null ? null : Date.valueOf(event.getEndDate()));
            ps.setInt(6, event.getCapacity());
            ps.setInt(7, findAndAddCategory(event.getCategory()).getCategoryId());
            ps.setInt(8, findAndAddCulture(event.getCulture()).getCultureId());
            ps.setString(9, event.getUsername());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        event.setEventId(keyHolder.getKey().intValue());

        return event;
    }

    @Override
    public boolean update(Event event) {
        final String sql = "update event_table set "
                + "event_name = ?, "
                + "location_id = ?, "
                + "start_date = ?,"
                + "end_date = ?, "
                + "capacity = ?, "
                + "category_id = ?, "
                + "culture_id = ? "
                + "where event_id = ?;";
        Location location = findAndAddLocation(event.getFullAddress());


        return jdbcTemplate.update(sql,
                event.getEventName(),
                location == null ? null : location.getLocationId(),
                event.getStartDate(),
                event.getEndDate(),
                event.getCapacity(),
                findAndAddCategory(event.getCategory()).getCategoryId(),
                findAndAddCulture(event.getCulture()).getCultureId(),
                event.getEventId()
        ) > 0;
    }


    @Override
    @Transactional
    public boolean deleteById(int eventId) {
        jdbcTemplate.update("delete from rsvp where event_id = ?;", eventId);
        jdbcTemplate.update("delete from event_performance where event_id = ?;", eventId);
        return jdbcTemplate.update("delete from event_table where event_id = ?;", eventId) > 0;
    }

    private Location findAndAddLocation(String fullAddress){
        if(Validations.isNullOrBlank(fullAddress)){return null;}

        String[] locList = fullAddress.split(",");
        Location location = new Location(0, locList[0].trim(), locList[1].trim(), locList[2].trim(), locList[3].trim());

        Location returnedLocation = locationRepo.findByAddress(location);

        if(returnedLocation==null){
            returnedLocation = locationRepo.add(location);
        }
        return returnedLocation;
    }

    private EventCategory findAndAddCategory(String categoryName){
        EventCategory returnedCategory = catRepo.findByName(categoryName);

        if(returnedCategory==null){
            EventCategory category = new EventCategory(0, categoryName, categoryName);
            returnedCategory = catRepo.add(category);
        }
        return returnedCategory;
    }

    private EventCulture findAndAddCulture(String cultureName){
        EventCulture returnedCulture = culRepo.findByName(cultureName);

        if(returnedCulture==null){
            EventCulture culture = new EventCulture(0,cultureName,null);
            returnedCulture = culRepo.add(culture);
        }
        return returnedCulture;
    }
}
