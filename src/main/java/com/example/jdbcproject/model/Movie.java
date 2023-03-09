package com.example.jdbcproject.model;

import java.time.LocalDate;
import java.util.List;

public class Movie {

    private Integer id;
    private String name;
    private LocalDate releaseDate;
    private List<Actor> actors;

    public Movie() {
    }

    public Movie(Integer id, String name, LocalDate releaseDate, List<Actor> actors) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.actors = actors;
    }

    public Movie(String name, LocalDate releaseDate, List<Actor> actors) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.actors = actors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", actors=" + actors +
                '}';
    }
}
