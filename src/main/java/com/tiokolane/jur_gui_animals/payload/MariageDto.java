package com.tiokolane.jur_gui_animals.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MariageDto {
    private Long male_id;
    private Long femelle_id;
    private Date date_mariage;
    
}
