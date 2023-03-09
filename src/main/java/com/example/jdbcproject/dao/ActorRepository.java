package com.example.jdbcproject.dao;

import com.example.jdbcproject.model.Actor;
import com.example.jdbcproject.rowmapper.ActorRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ActorRepository implements ActorDAO{

    private final JdbcTemplate jdbcTemplate;

    public ActorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Actor> selectActors() {
        String SQL = "SELECT id, name FROM actor;";
        return jdbcTemplate.query(SQL, new ActorRowMapper());
    }

    @Override
    public Optional<Actor> selectActorById(Integer id) {
        String SQL = "SELECT id, name FROM actor WHERE id = ?;";
        return jdbcTemplate.query(SQL, new ActorRowMapper(), id).stream().findFirst();
    }

    @Override
    public Optional<Actor> selectActorByName(String name) {
        String SQL = "SELECT id, name FROM actor WHERE name = ?;";
        return jdbcTemplate.query(SQL, new ActorRowMapper(), name).stream().findFirst();
    }

    @Override
    public int insertActor(Actor actor) {
        String SQL = "INSERT INTO actor (name) VALUES (?);";
        return jdbcTemplate.update(SQL, actor.getName());
    }

    @Override
    public int deleteActorById(Integer id) {
        String SQL = "DELETE FROM actor WHERE id = ?;";
        return jdbcTemplate.update(SQL, id);
    }

    @Override
    public int updateActor(Actor actor) {
        String SQL = "UPDATE actor SET name = ? WHERE id = ?;";
        return jdbcTemplate.update(SQL, actor.getName(), actor.getId());
    }
}
