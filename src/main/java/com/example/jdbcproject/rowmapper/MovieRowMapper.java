package com.example.jdbcproject.rowmapper;

import com.example.jdbcproject.model.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        LocalDate date = LocalDate.parse(rs.getString("release_date"));

        return new Movie(id, name, date, List.of());
    }


}
