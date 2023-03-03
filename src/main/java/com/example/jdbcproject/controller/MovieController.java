package com.example.jdbcproject.controller;

import com.example.jdbcproject.model.Movie;
import com.example.jdbcproject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> listMovies() {
        return movieService.getMovies();
    }

    @GetMapping(path = "{id}")
    public Movie getMovie(@PathVariable("id") Integer id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        movieService.addNewMovie(movie);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteMovieById(id);
    }

    @PutMapping(path = "{id}")
    public void updateMovie(@PathVariable Integer id,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) LocalDate releaseDate) {
        movieService.updateMovieById(id, name, releaseDate);
    }
}
