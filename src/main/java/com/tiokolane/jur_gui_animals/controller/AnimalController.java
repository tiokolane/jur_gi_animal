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
import com.tiokolane.jur_gui_animals.model.Race;
import com.tiokolane.jur_gui_animals.model.Animal;
import com.tiokolane.jur_gui_animals.payload.AnimalDto;
import com.tiokolane.jur_gui_animals.repository.AnimalRepository;
import com.tiokolane.jur_gui_animals.repository.RaceRepository;

@RestController
@RequestMapping("/api")
public class AnimalController {
  @Autowired
  AnimalRepository animalRepository;

  @Autowired
  RaceRepository raceRepository;

  @GetMapping("/animals")
  public ResponseEntity<List<Animal>> getAllAnimals(@RequestParam(required = false) String name) {
    try {
      List<Animal> animals = new ArrayList<Animal>();

        animalRepository.findAll().forEach(animals::add);
      

      if (animals.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(animals, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/animal/{id}")
  public ResponseEntity<Animal> getAnimalById(@PathVariable("id") long id) {
    Optional<Animal> animalData = animalRepository.findById(id);

    if (animalData.isPresent()) {
      return new ResponseEntity<>(animalData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/animal/{owner_id}")
  public ResponseEntity<Animal> getAnimalByOwnerId(@PathVariable("owner_id") long owner_id) {
    Optional<Animal> animalData = animalRepository.findById(owner_id);

    if (animalData.isPresent()) {
      return new ResponseEntity<>(animalData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  

  @PostMapping("/animal")
  public ResponseEntity<ResponseMessage> createAnimal(@RequestBody AnimalDto animalDto) {
    try {
      Animal _animal = new Animal();
      _animal.setDescription(animalDto.getDescription());
      _animal.setName(animalDto.getName());
      _animal.setOwner_id(animalDto.getOwner_id());
      try {
        Race race = raceRepository.getById(animalDto.getRace_id());
      _animal.setRace(race);
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseMessage(e.getMessage()));
      }
      
      
      animalRepository.save(_animal);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Animal Ajoutée avec succès"));

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/animals/{id}")
  public ResponseEntity<Animal> updateAnimal(@PathVariable("id") long id, @RequestBody AnimalDto animalDto) {
    Optional<Animal> animalData = animalRepository.findById(id);

    if (animalData.isPresent()) {
      Animal _animal = animalData.get();
      if(animalDto.getName() != null){
        _animal.setName(animalDto.getName()) ;
      }
      if(animalDto.getDescription() != null){
        _animal.setDescription(animalDto.getDescription());
      }
      if(animalDto.getRace_id() != null){
        Race race = raceRepository.getById(animalDto.getRace_id());
        _animal.setRace(race);
      }
      if(animalDto.getOwner_id() != null){
        _animal.setOwner_id(animalDto.getOwner_id());
      }

      return new ResponseEntity<>(animalRepository.save(_animal), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/animals/{id}")
  public ResponseEntity<HttpStatus> deleteAnimal(@PathVariable("id") long id) {
    try {
      animalRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/animals")
  public ResponseEntity<HttpStatus> deleteAllAnimals() {
    try {
      animalRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }



}

