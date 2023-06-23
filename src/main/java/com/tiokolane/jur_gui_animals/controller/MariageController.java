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
import org.springframework.web.bind.annotation.RestController;

import com.tiokolane.jur_gui_animals.exception.ResponseMessage;
import com.tiokolane.jur_gui_animals.model.Animal;
import com.tiokolane.jur_gui_animals.model.Mariage;
import com.tiokolane.jur_gui_animals.payload.MariageDto;
import com.tiokolane.jur_gui_animals.repository.AnimalRepository;
import com.tiokolane.jur_gui_animals.repository.MariageRepository;

@RestController
@RequestMapping("/api")
public class MariageController {
  @Autowired
  MariageRepository mariageRepository;

  @Autowired
  AnimalRepository animalRepository;

  @GetMapping("/mariages")
  public ResponseEntity<List<Mariage>> getAllMariages() {
    try {
      List<Mariage> mariages = new ArrayList<Mariage>();

        mariageRepository.findAll().forEach(mariages::add);
      

      if (mariages.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(mariages, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/mariage/{id}")
  public ResponseEntity<Mariage> getMariageById(@PathVariable("id") long id) {
    Optional<Mariage> mariageData = mariageRepository.findById(id);

    if (mariageData.isPresent()) {
      return new ResponseEntity<>(mariageData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/mariage")
  public ResponseEntity<ResponseMessage> createMariage(@RequestBody MariageDto mariageDto) {
    try {
      Mariage _mariage = new Mariage();
      _mariage.setDate_mariage(mariageDto.getDate_mariage());
      try {
        Animal male = animalRepository.getById(mariageDto.getMale_id());
      _mariage.setMale(male);
      Animal femelle = animalRepository.getById(mariageDto.getFemelle_id());
      _mariage.setFemelle(femelle);

      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(new ResponseMessage(e.getMessage()));
      }
      
      
      mariageRepository.save(_mariage);
      return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Mariage Ajoutée avec succès"));

    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/mariages/{id}")
  public ResponseEntity<Mariage> updateMariage(@PathVariable("id") long id, @RequestBody MariageDto mariageDto) {
    Optional<Mariage> mariageData = mariageRepository.findById(id);

    if (mariageData.isPresent()) {
      Mariage _mariage = mariageData.get();
      if(mariageDto.getDate_mariage() != null){
        _mariage.setDate_mariage(mariageDto.getDate_mariage());
      }
     
      if(mariageDto.getMale_id() != null){
        Animal male = animalRepository.getById(mariageDto.getMale_id());
        _mariage.setMale(male);
      }
      if(mariageDto.getMale_id() != null){
        Animal femelle = animalRepository.getById(mariageDto.getFemelle_id());
        _mariage.setFemelle(femelle);
      }

      return new ResponseEntity<>(mariageRepository.save(_mariage), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/mariages/{id}")
  public ResponseEntity<HttpStatus> deleteMariage(@PathVariable("id") long id) {
    try {
      mariageRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/mariages")
  public ResponseEntity<HttpStatus> deleteAllMariages() {
    try {
      mariageRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }



}

