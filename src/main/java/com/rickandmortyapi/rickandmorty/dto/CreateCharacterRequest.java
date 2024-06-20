package com.rickandmortyapi.rickandmorty.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCharacterRequest {
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private CharacterLocationDto location;
}
