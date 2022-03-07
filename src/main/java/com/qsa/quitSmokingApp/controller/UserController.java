package com.qsa.quitSmokingApp.controller;


import com.qsa.quitSmokingApp.logic.AppUserService;
import com.qsa.quitSmokingApp.model.AppUser;
import com.qsa.quitSmokingApp.model.projection.AppUserReadModel;
import com.qsa.quitSmokingApp.model.projection.NewUserWriteModel;
import com.qsa.quitSmokingApp.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository repository;
    private final AppUserService appUserService;

    UserController(final UserRepository repository, final AppUserService appUserService){
        this.repository = repository;
        this.appUserService = appUserService;
    }

    @GetMapping(value = "/users", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<AppUser>> readAllUsers(){
        logger.warn("exposing all users");
        var result = repository.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/users/{id}")
    ResponseEntity<?> readUserById(@PathVariable int id){
        logger.info("exposing user by id");
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findById(id));
    }

    @PostMapping(value = "/users")
    ResponseEntity<AppUser> createUser(@RequestBody @Valid NewUserWriteModel newUserWriteModel){
        AppUser result = appUserService.createNewUser(newUserWriteModel);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }
}
