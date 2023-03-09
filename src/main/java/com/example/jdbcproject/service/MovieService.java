package com.example.jdbcproject.service;

import com.example.jdbcproject.dao.ActorDAO;
import com.example.jdbcproject.dao.ActorMovieDao;
import com.example.jdbcproject.dao.MovieDao;
import com.example.jdbcproject.exception.NotFoundException;
import com.example.jdbcproject.model.Actor;
import com.example.jdbcproject.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    private final MovieDao movieDao;
    private final ActorDAO actorDAO;
    private final ActorMovieDao actorMovieDao;

    @Autowired
    public MovieService(MovieDao movieDao, ActorDAO actorDAO, ActorMovieDao actorMovieDao) {
        this.movieDao = movieDao;
        this.actorDAO = actorDAO;
        this.actorMovieDao = actorMovieDao;
    }

    public List<Movie> getMovies() {
        return movieDao.selectMovies();
    }

    public Movie getMovieById(Integer id) {
        return movieDao.selectMovieById(id)
                .orElseThrow(() -> new NotFoundException("Movie not found"));
    }

    public void addNewMovie(Movie movie) { // Can't add a list of actors for a movie
        if (movieDao.selectMovieByName(movie.getName()).isPresent()) {
            throw new IllegalStateException("Movie already added");
        }
        int result = movieDao.insertMovie(movie);
        if (result != 1) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public void deleteMovieById(Integer id) {
        if (movieDao.selectMovieById(id).isPresent()) {
            if (actorMovieDao.selectByMovieId(id).isPresent()) {
                int result = actorMovieDao.deleteActorMovieByMovieId(id);
                if (result != 1) {
                    throw new IllegalStateException("Something went wrong");
                }
            }
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
        int result = movieDao.updateMovie(movie);
        if (result != 1) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public void assignActorToMovie(Integer movieId, Integer actorId) {
        Movie movie = movieDao.selectMovieById(movieId)
                .orElseThrow(() -> new NotFoundException("Movie not found"));
        Actor actor = actorDAO.selectActorById(actorId)
                .orElseThrow(() -> new NotFoundException("Actor not found"));
        if (actorMovieDao.selectByMovieIdAndActorId(movieId, actorId).isPresent()) {
            throw new IllegalStateException("Actor already in this movie");
        }
        int result = actorMovieDao.insertActorMovie(movieId, actorId);
        if (result != 1) {
            throw new IllegalStateException("Something went wrong");
        }
    }
}
