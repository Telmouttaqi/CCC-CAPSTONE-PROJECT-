package ccc.data;

import ccc.data.mappers.UserMapper;
import ccc.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserJdbcTemplateRepository implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAllUsers() {
        final String sql = "select user_id, first_name, middle_name, last_name, user_address," +
                " user_city, user_state, user_zip, user_phone, user_email "
                + "from user limit 1000;";
        return jdbcTemplate.query(sql, new UserMapper());

    }


//    @Override
//    @Transactional
//    public List<User> findById(int userId) {
//        final String sql = "select user_id, first_name, middle_name, last_name, user_address," +
//                " user_city, user_state, user_zip, user_phone, user_email "
//                + "from user "
//                + "where user_id = ?;";
//        return jdbcTemplate.query(sql, new UserMapper(), userId);
//
//    }

    @Override
    public User findByUserId(int userId) {
        final String sql = "select user_id, first_name, middle_name, last_name, user_address," +
                " user_city, user_state, user_zip, user_phone, user_email "
                + "from user "
                + "where user_id = ?;";
        User user =  jdbcTemplate.query(sql, new UserMapper(), userId).stream()
                .findFirst().orElse(null);

        return user;
    }

    @Override
    public User add(User user) {
        final String sql = "insert into user (first_name, middle_name, last_name, user_address, " +
                "user_city, user_state, user_zip, user_phone, user_email) "
                + " values (?,?,?,?,?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getMiddleName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getUserAddress());
            ps.setString(5, user.getUserCity());
            ps.setString(6,user.getUserState());
            ps.setString(7,user.getUserZip());
            ps.setString(8,user.getUserPhone());
            ps.setString(9,user.getUserEmail());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        user.setUserId(keyHolder.getKey().intValue());
        return user;
    }

    @Override
    public boolean update(User user) {
        final String sql = "update user set "
                + " first_name = ?, "
                + " middle_name = ?, "
                + " last_name = ?, "
                + " user_address = ?, "
                + " user_city = ?, "
                + " user_state = ?, "
                + " user_zip = ?, "
                + " user_phone = ?, "
                + " user_email = ? "
                + " where user_id = ?;";

        return jdbcTemplate.update(sql,
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getUserAddress(),
                user.getUserCity(),
                user.getUserState(),
                user.getUserZip(),
                user.getUserPhone(),
                user.getUserEmail(),
                user.getUserId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteById(int userId) {

        return jdbcTemplate.update("DELETE FROM user where user_id = ?;", userId) > 0;

    }

}