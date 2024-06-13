package com.rickandmortyapi.rickandmorty.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDto {

    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private CharacterLocationDto location;
}
