package com.tiokolane.jur_gui_animals.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiokolane.jur_gui_animals.model.Race;

public interface RaceRepository extends JpaRepository<Race, Long> {
    Optional<Race> findByName(String name);
    List<Race> findByPublished(boolean published);
}
    

