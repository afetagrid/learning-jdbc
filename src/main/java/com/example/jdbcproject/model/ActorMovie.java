package com.example.jdbcproject.model;

public class ActorMovie {

    private Integer actor_id;
    private Integer movie_id;

    public ActorMovie() {
    }

    public ActorMovie(Integer actor_id, Integer movie_id) {
        this.actor_id = actor_id;
        this.movie_id = movie_id;
    }

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    @Override
    public String toString() {
        return "ActorMovie{" +
                "actor_id=" + actor_id +
                ", movie_id=" + movie_id +
                '}';
    }
}
