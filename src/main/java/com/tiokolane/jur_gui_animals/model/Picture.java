package com.tiokolane.jur_gui_animals.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter

@Getter

@AllArgsConstructor

@NoArgsConstructor

@ToString
@Entity
@Table(name = "Picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private Long pictureId;

    @Column(name = "url")
	private String url;


    @ManyToOne
    @JoinColumn(name = "category_id",nullable=true)
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "race_id",nullable=true, referencedColumnName = "id")
    @JsonBackReference
    private Race race;

    @ManyToOne
    @JoinColumn(name = "animal_id",nullable=true, referencedColumnName = "id")
    @JsonBackReference
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "mariage_id",nullable=true, referencedColumnName = "id")
    @JsonBackReference
    private Mariage mariage;
}