package com.tiokolane.jur_gui_animals.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tiokolane.jur_gui_animals.exception.ResponseMessage;
import com.tiokolane.jur_gui_animals.model.Category;
import com.tiokolane.jur_gui_animals.model.Race;
import com.tiokolane.jur_gui_animals.payload.RaceDto;
import com.tiokolane.jur_gui_animals.repository.CategoryRepository;
import com.tiokolane.jur_gui_animals.repository.RaceRepository;

@RestController
@RequestMapping("/api")
public class RaceController {
  @Autowired
  RaceRepository raceRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @GetMapping("/races")
  public ResponseEntity<List<Race>> getAllRaces(@RequestParam(required = false) String name) {
    try {
      List<Race> races = new ArrayList<Race>();

        raceRepository.findAll().forEach(races::add);
      

      if (races.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(races, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/race/{id}")
  public ResponseEntity<Race> getRaceById(@PathVariable("id") long id) {
    Optional<Race> raceData = raceRepository.findById(id);

    if (raceData.isPresent()) {
      return new ResponseEntity<>(raceData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/race")
  public ResponseEntity<ResponseMessage> createRace(@RequestBody RaceDto raceDto) {
    try {
      Race _race = new Race();
      _race.setDescription(raceDto.getDescription());
      _race.setName(raceDto.getName());
      try {
        Category category = categoryRepository.getById(raceDto.getCategory_id());
      _race.setCategory(category);
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseMessage(e.getMessage()));
      }
      
      _race.setPublished(false);
      raceRepository.save(_race);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Race Ajoutée avec succès"));

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/races/{id}")
  public ResponseEntity<Race> updateRace(@PathVariable("id") long id, @RequestBody RaceDto raceDto) {
    Optional<Race> raceData = raceRepository.findById(id);

    if (raceData.isPresent()) {
      Race _race = raceData.get();
      if(raceDto.getName() != null){
        _race.setName(raceDto.getName()) ;
      }
      if(raceDto.getDescription() != null){
        _race.setDescription(raceDto.getDescription());
      }
      if(raceDto.getCategory_id() != null){
        Category category = categoryRepository.getById(raceDto.getCategory_id());
        _race.setCategory(category);
      }
      _race.setPublished(raceDto.isPublished());

      return new ResponseEntity<>(raceRepository.save(_race), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/races/{id}")
  public ResponseEntity<HttpStatus> deleteRace(@PathVariable("id") long id) {
    try {
      raceRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/races")
  public ResponseEntity<HttpStatus> deleteAllRaces() {
    try {
      raceRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  @GetMapping("/races/published")
  public ResponseEntity<List<Race>> findByPublished() {
    try {
      List<Race> races = raceRepository.findByPublished(true);

      if (races.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(races, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}

