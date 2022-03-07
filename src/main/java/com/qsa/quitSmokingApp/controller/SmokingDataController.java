package com.qsa.quitSmokingApp.controller;

import com.qsa.quitSmokingApp.model.SmokingData;
import com.qsa.quitSmokingApp.model.repository.SmokingDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class SmokingDataController {

    private static final Logger logger = LoggerFactory.getLogger(SmokingDataController.class);
    private final SmokingDataRepository repository;

    SmokingDataController(final SmokingDataRepository repository){
        this.repository = repository;
    }

    @GetMapping("/smkdata")
    ResponseEntity<List<SmokingData>> readAllSmokingData(){
        logger.warn("exposing all smoking data");
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/smkdata/{id}")
    ResponseEntity<?> readSmokingDataById(@PathVariable Integer id){
        logger.warn("exposing smoking data by id");
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repository.findById(id));
    }

    @PostMapping("/smkdata")
    ResponseEntity<SmokingData> createSmokingData(@RequestBody @Valid SmokingData toCreate){
        logger.info("creating new smoking data");
        SmokingData result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

}
