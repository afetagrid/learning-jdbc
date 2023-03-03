package com.example.jdbcproject.dao;

import com.example.jdbcproject.model.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieDao {
    List<Movie> selectMovies();
    Optional<Movie> selectMovieById(Integer id);
    int insertMovie(Movie movie);
    int deleteMovieById(Integer id);
    int updateMovie(Movie movie);
}
