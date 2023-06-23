package com.tiokolane.jur_gui_animals.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiokolane.jur_gui_animals.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByName(String name);
    
}
    

