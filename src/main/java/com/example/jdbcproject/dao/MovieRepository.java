package com.example.jdbcproject.dao;

import com.example.jdbcproject.model.Actor;
import com.example.jdbcproject.model.Movie;
import com.example.jdbcproject.rowmapper.MovieRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper());
        for (Movie movie : movies) {
            String SQL = """
                SELECT a.id, a.name
                FROM actor a
                INNER JOIN actor_movie am ON a.id = am.actor_id
                WHERE am.movie_id = ?;
                """;
            List<Actor> actors = jdbcTemplate.query(SQL, (resultSet, i) -> new Actor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    List.of()
            ),movie.getId());
            movie.setActors(actors);
        }
        return movies;
    }

    @Override
    public Optional<Movie> selectMovieById(Integer id) {
        String sql = """
                SELECT id, name, release_date
                FROM movie
                WHERE id = ?;
                """;
        Optional<Movie> movie = jdbcTemplate.query(sql, new MovieRowMapper(), id).stream().findFirst();
        if (movie.isPresent()) {
            String SQL = """
                SELECT a.id, a.name
                FROM actor a
                INNER JOIN actor_movie am ON a.id = am.actor_id
                WHERE am.movie_id = ?;
                """;
            List<Actor> actors = jdbcTemplate.query(SQL, (resultSet, i) -> new Actor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    List.of()
            ), movie.get().getId());
            movie.get().setActors(actors);
        }
        return movie;
    }

    @Override
    public Optional<Movie> selectMovieByName(String name) {
        String sql = """
                SELECT id, name, release_date
                FROM movie
                WHERE name = ?;
                """;
        return jdbcTemplate.query(sql, new MovieRowMapper(), name).stream().findFirst();
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
