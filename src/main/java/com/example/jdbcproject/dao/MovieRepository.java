package com.example.jdbcproject.dao;

import com.example.jdbcproject.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepository implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        String sql = "SELECT id, name, release_date FROM movie;";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new Movie(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                LocalDate.parse(resultSet.getString("release_date"))
        ));
    }

    @Override
    public Optional<Movie> selectMovieById(Integer id) {
        String sql = """
                SELECT id, name, release_date
                FROM movie
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new Movie(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                LocalDate.parse(resultSet.getString("release_date"))
            ), id)
                .stream()
                .findFirst();
    }

    @Override
    public int insertMovie(Movie movie) {
        String sql = """
                INSERT INTO movie (name, release_date)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(sql, movie.getName(), movie.getReleaseDate());
    }

    @Override
    public int deleteMovieById(Integer id) {
        String sql = """
                DELETE FROM movie
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateMovie(Movie movie) {
        String sql = """
                UPDATE movie
                SET name = ?, release_date = ?
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, movie.getName(), movie.getReleaseDate(), movie.getId());
    }
}
