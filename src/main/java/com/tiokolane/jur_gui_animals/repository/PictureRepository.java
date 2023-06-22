package com.tiokolane.jur_gui_animals.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiokolane.jur_gui_animals.model.Category;
import com.tiokolane.jur_gui_animals.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    Optional<Picture> findByUrl(String url);
    // List<Picture> findByCategory(Long categoryId);
}
    

