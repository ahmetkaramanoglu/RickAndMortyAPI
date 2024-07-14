package com.rickandmortyapi.rickandmorty.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterDto {
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private CharacterLocationDto location;
}
