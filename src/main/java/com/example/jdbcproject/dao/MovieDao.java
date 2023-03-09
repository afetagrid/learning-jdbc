package com.example.jdbcproject.dao;

import com.example.jdbcproject.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieDao {
    List<Movie> selectMovies();
    Optional<Movie> selectMovieById(Integer id);
    Optional<Movie> selectMovieByName(String name);
    int insertMovie(Movie movie);
    int deleteMovieById(Integer id);
    int updateMovie(Movie movie);
}
