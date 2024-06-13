package com.rickandmortyapi.rickandmorty.dto;

import com.rickandmortyapi.rickandmorty.model.Character;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    private String name;
    private String type;
    private String dimension;
    private Set<CharacterDto> residents;

}
