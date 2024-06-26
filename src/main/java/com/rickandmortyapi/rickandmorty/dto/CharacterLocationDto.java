package com.rickandmortyapi.rickandmorty.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterLocationDto {
    private String name;
    private String type;
    private String dimension;
}
