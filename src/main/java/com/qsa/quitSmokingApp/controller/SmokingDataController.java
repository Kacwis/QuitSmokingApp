package com.qsa.quitSmokingApp.controller;

import com.qsa.quitSmokingApp.logic.SmokingDataService;
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
    private final SmokingDataService service;

    SmokingDataController(final SmokingDataService service){
        this.service = service;
    }

    @GetMapping("/smkdata")
    ResponseEntity<List<SmokingData>> readAllSmokingData(){
        logger.warn("exposing all smoking data");
        return ResponseEntity.ok(service.getAllSmokingData());
    }

    @GetMapping("/smkdata/{id}")
    ResponseEntity<?> readSmokingDataById(@PathVariable Integer id){
        logger.warn("exposing smoking data by id");
        var result = service.getSmokingDataById(id);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/smkdata")
    ResponseEntity<SmokingData> createSmokingData(@RequestBody @Valid SmokingData toCreate){
        logger.info("creating new smoking data");
        var result = service.createSmokingData(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

}
