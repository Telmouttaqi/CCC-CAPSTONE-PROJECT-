package ccc.data.mappers;

import ccc.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setMiddleName(rs.getString("middle_name"));
        user.setLastName(rs.getString("last_name"));
        user.setUserAddress(rs.getString("user_address"));
        user.setUserCity(rs.getString("user_city"));
        user.setUserState(rs.getString("user_state"));
        user.setUserZip(rs.getString("user_zip"));
        user.setUserPhone(rs.getString("user_phone"));
        user.setUserEmail(rs.getString("user_email"));

        return user;
    }

}