package com.tiokolane.jur_gui_animals.model;

import java.util.Date;
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
@Table(name = "mariages")
public class Mariage {
    

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @PrimaryKeyJoinColumn
	private long id;

	@Column(name = "description")
	private String description;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "mariage_male",
             joinColumns = @JoinColumn(name = "mariage_id"),
             inverseJoinColumns = @JoinColumn(name = "male_id"))
    private Animal male;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "mariage_femelle",
             joinColumns = @JoinColumn(name = "mariage_id"),
             inverseJoinColumns = @JoinColumn(name = "femelle_id"))
    private Animal femelle;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "mariage")
    @JsonManagedReference
    private List<Picture> pictures;
    
    @Column(name = "date")
    private Date date_mariage;
}
