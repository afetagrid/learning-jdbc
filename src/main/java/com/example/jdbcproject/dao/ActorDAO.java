package com.example.jdbcproject.dao;

import com.example.jdbcproject.model.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorDAO {
    List<Actor> selectActors();
    Optional<Actor> selectActorById(Integer id);
    Optional<Actor> selectActorByName(String name);
    int insertActor(Actor actor);
    int deleteActorById(Integer id);
    int updateActor(Actor actor);
}
