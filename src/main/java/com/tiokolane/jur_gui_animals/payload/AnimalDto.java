package com.tiokolane.jur_gui_animals.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalDto {
    private String name;
    private String description;
    private Long race_id;
    private Long owner_id;
    
}
