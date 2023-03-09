package com.example.jdbcproject.dao;

import com.example.jdbcproject.model.ActorMovie;

import java.util.Optional;

public interface ActorMovieDao {
    Optional<ActorMovie> selectByMovieIdAndActorId(Integer movieId, Integer actorId);
    Optional<ActorMovie> selectByMovieId(Integer movieId);
    int insertActorMovie(Integer movieId, Integer actorId);
    int deleteActorMovieByMovieId(Integer movieId);
}
