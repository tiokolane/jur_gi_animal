package com.tiokolane.jur_gui_animals.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
@Table(name = "animals")
public class Animal {
    

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @PrimaryKeyJoinColumn
	private long id;

	@Column(name = "name")
	private String name;

    @Column(name = "sexe")
	private String sexe;

	@Column(name = "description")
	private String description;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "animal_race",
             joinColumns = @JoinColumn(name = "animal_id"),
             inverseJoinColumns = @JoinColumn(name = "race_id"))
    private Race race;
    
    private long owner_id;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "animal")
    @JsonManagedReference
    private List<Picture> pictures;
}
