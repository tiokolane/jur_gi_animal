package com.tiokolane.jur_gui_animals.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiokolane.jur_gui_animals.model.Animal;
import com.tiokolane.jur_gui_animals.model.Mariage;

public interface MariageRepository extends JpaRepository<Mariage, Long> {
    
}
    

