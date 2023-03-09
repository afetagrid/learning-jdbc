package com.example.jdbcproject.controller;

import com.example.jdbcproject.model.Actor;
import com.example.jdbcproject.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    List<Actor> listActors() {
        return actorService.getAllActors();
    }

    @GetMapping(path = "{id}")
    Actor getActor(@PathVariable("id") Integer id) {
        return actorService.getActorById(id);
    }

    @PostMapping
    void addActor(@RequestBody Actor actor) {
        actorService.addNewActor(actor);
    }

    @DeleteMapping(path = "{id}")
    void deleteActor(@PathVariable("id") Integer id) {
        actorService.deleteActorById(id);
    }

    @PutMapping(path = "{id}")
    void updateActor(@PathVariable("id") Integer id,
                     @RequestParam(required = false) String name) {
        actorService.updateActorById(id, name);
    }
}
