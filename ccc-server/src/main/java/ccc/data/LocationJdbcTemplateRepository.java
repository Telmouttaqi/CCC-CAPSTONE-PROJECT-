package ccc.data;

import ccc.data.mappers.LocationMapper;
import ccc.models.Location;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class LocationJdbcTemplateRepository implements LocationRepository {

    private final JdbcTemplate jdbcTemplate;

    public LocationJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Location> findAll() {
        final String sql = "select "
                + " location_id, address, city, state, zip_code  "
                + " from location "
                + " limit 1000;";
        return jdbcTemplate.query(sql, new LocationMapper());
    }

    @Override
    public Location findById(int locationId) {
        final String sql = "select location_id, address, city, state, zip_code "
                            + " from location "
                            + " where location_id = ?;";

        return jdbcTemplate.query(sql, new LocationMapper(), locationId).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Location add(Location location) {
        final String sql = "insert into location (address, city, state, zip_code) "
                + " values (?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, location.getAddress());
            ps.setString(2, location.getCity());
            ps.setString(3, location.getState());
            ps.setString(4, location.getZipCode());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        location.setLocationId(keyHolder.getKey().intValue());
        return location;
    }

    @Override
    public Boolean update(Location location) {
        final String sql = "update location set "
                + " address = ?, "
                + " city = ?, "
                + " state = ?,"
                + " zip_code = ? "
                + " where location_id = ?;";

        return jdbcTemplate.update(sql,
                location.getAddress(),
                location.getCity(),
                location.getState(),
                location.getZipCode(),
                location.getLocationId())  > 0;

    }

    @Override
    public Boolean deleteById(int locationId) {
        return jdbcTemplate.update("DELETE FROM location where location_id = ?;", locationId) > 0;
    }
    @Override
    public Location findByZipCode(String zipCode) {
        final String sql = "select location_id, address, city, state, zip_code "
                + " from location "
                + " where zip_code = ?;";

        return jdbcTemplate.query(sql, new LocationMapper(), zipCode).stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Location findByAddress(Location location){
        final String sql = "select location_id, address, city, state, zip_code "
                + " from location "
                + " where address = ? and city = ? and state = ? and zip_code = ?;";

        return jdbcTemplate.query(sql, new LocationMapper(), location.getAddress(), location.getCity(),
                        location.getState(), location.getZipCode()).stream()
                .findFirst()
                .orElse(null);
    }
}
