package com.example.jdbcproject.dao;

import com.example.jdbcproject.model.ActorMovie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ActorMovieRepository implements ActorMovieDao{

    private final JdbcTemplate jdbcTemplate;

    public ActorMovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<ActorMovie> selectByMovieIdAndActorId(Integer movieId, Integer actorId) {
        String sql = """
                SELECT actor_id, movie_id
                FROM actor_movie
                WHERE actor_id = ? AND movie_id = ?;
                """;
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new ActorMovie(
                        resultSet.getInt("actor_id"),
                        resultSet.getInt("movie_id")
                ), actorId, movieId)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<ActorMovie> selectByMovieId(Integer movieId) {
        String sql = """
                SELECT actor_id, movie_id
                FROM actor_movie
                WHERE movie_id = ?;
                """;
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new ActorMovie(
                        resultSet.getInt("actor_id"),
                        resultSet.getInt("movie_id")
                ), movieId)
                .stream()
                .findFirst();
    }

    @Override
    public int insertActorMovie(Integer movieId, Integer actorId) {
        String sql = """
                INSERT INTO actor_movie (actor_id, movie_id)
                VALUES (?, ?);
                """;
        return jdbcTemplate.update(sql, actorId, movieId);
    }

    @Override
    public int deleteActorMovieByMovieId(Integer movieId) {
        String SQL = """
                DELETE FROM actor_movie
                WHERE movie_id = ?;
                """;
        return jdbcTemplate.update(SQL, movieId);
    }
}
