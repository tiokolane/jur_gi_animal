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
@Table(name = "categories")
public class Category {
    

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

    @Column(name = "published")
	private boolean published;

	// @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "category")
    // @JsonManagedReference
    // private List<Race> races;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "category")
    @JsonManagedReference
    private List<Picture> pictures;
}
