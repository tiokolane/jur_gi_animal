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
@Table(name = "races")
public class Race {
    

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @PrimaryKeyJoinColumn
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

    @Column(name = "published")
	private boolean published;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "category_race",
             joinColumns = @JoinColumn(name = "race_id"),
             inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category category;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "race")
    @JsonManagedReference
    private List<Picture> pictures;
}
