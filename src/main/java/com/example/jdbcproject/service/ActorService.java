package com.example.jdbcproject.service;

import com.example.jdbcproject.dao.ActorDAO;
import com.example.jdbcproject.exception.NotFoundException;
import com.example.jdbcproject.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorDAO actorDAO;

    @Autowired
    public ActorService(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }

    public List<Actor> getAllActors() { // Doesn't return the list of movies for every actor
        return actorDAO.selectActors();
    }

    public Actor getActorById(Integer id) { // Same as getAllActors
        return actorDAO.selectActorById(id)
                .orElseThrow(() -> new NotFoundException("Actor not found"));
    }

    public void addNewActor(Actor actor) {  // Can't add a list of movies for an actor
        if (actorDAO.selectActorByName(actor.getName()).isPresent()) {
            throw new IllegalStateException("Actor already added");
        }
        int result = actorDAO.insertActor(actor);
        if (result != 1) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public void deleteActorById(Integer id) {   // Should delete the data in actor_movie first
        if (actorDAO.selectActorById(id).isPresent()) {
            int result = actorDAO.deleteActorById(id);
            if (result != 1) {
                throw new IllegalStateException("Something went wrong");
            }
        } else {
            throw new NotFoundException("Actor not found");
        }
    }

    public void updateActorById(Integer id, String name) {
        Actor actor = actorDAO.selectActorById(id)
                .orElseThrow(() -> new NotFoundException("Actor not found"));
        if (name != null &&
            name.length() > 0 &&
            !name.equals(actor.getName())) {
            actor.setName(name);
        }
        int result = actorDAO.updateActor(actor);
        if (result != 1) {
            throw new IllegalStateException("Something went wrong");
        }
    }
}
