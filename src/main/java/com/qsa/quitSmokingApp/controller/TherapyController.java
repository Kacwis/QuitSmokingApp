package com.qsa.quitSmokingApp.controller;

import com.qsa.quitSmokingApp.logic.TherapyService;
import com.qsa.quitSmokingApp.model.Therapy;
import com.qsa.quitSmokingApp.model.projection.TherapyWriteModel;
import com.qsa.quitSmokingApp.model.repository.TherapyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/therapies")
public class TherapyController {

    final private TherapyRepository repository;
    final private TherapyService service;

    TherapyController(final TherapyRepository repository, final TherapyService service){
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Therapy>> readAllTherapies(){
        return ResponseEntity.ok(service.getAllTherapies());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> readTherapyById(@PathVariable int id ){
        return ResponseEntity.ok(service.getTherapyById(id));
    }

    @PostMapping
    ResponseEntity<?> createTherapy(@RequestBody @Valid TherapyWriteModel therapyWriteModel){
        var result = service.addNewTherapyToAppUserAndReturnTherapy(therapyWriteModel);
        return ResponseEntity.created(URI.create("/therapies/" + result.getId())).body(result);

    }

}
