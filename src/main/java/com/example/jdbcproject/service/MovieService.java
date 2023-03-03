package com.example.jdbcproject.service;

import com.example.jdbcproject.dao.MovieDao;
import com.example.jdbcproject.exception.NotFoundException;
import com.example.jdbcproject.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    private final MovieDao movieDao;

    @Autowired
    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public List<Movie> getMovies() {
        return movieDao.selectMovies();
    }

    public Movie getMovieById(Integer id) {
        return movieDao.selectMovieById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found"));
    }

    public void addNewMovie(Movie movie) {
        if (movieDao.selectMovieById(movie.getId()).isPresent()) {
            throw new IllegalStateException("Movie already added");
        }
        int result = movieDao.insertMovie(movie);
        if (result != 1) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public void deleteMovieById(Integer id) {
        if (movieDao.selectMovieById(id).isPresent()) {
            int result = movieDao.deleteMovieById(id);
            if (result != 1) {
                throw new IllegalStateException("Something went wrong");
            }
        } else {
            throw new NotFoundException("Movie not found");
        }
    }

    public void updateMovieById(Integer id, String name, LocalDate releaseDate) {
        Movie movie = movieDao.selectMovieById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found"));
        if (name != null  &&
            name.length() > 0 &&
            !name.equals(movie.getName())) {
            movie.setName(name);
        }
        if (releaseDate != null &&
            !releaseDate.equals(movie.getReleaseDate())) {
            movie.setReleaseDate(releaseDate);
        }
        movieDao.updateMovie(movie);
    }
}
