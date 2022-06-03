package ccc.data.mappers;

import ccc.models.Performances;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformancesMapper implements RowMapper<Performances> {

    @Override
    public Performances mapRow(ResultSet rs, int rowNum) throws SQLException {
        Performances performances = new Performances();

        performances.setPerformancesId(rs.getInt("performance_id"));
        performances.setDescription(rs.getString("description"));
        performances.setName(rs.getString("name"));

        return performances;


    }


}
