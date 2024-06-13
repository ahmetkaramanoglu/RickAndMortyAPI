package com.rickandmortyapi.rickandmorty.dto.converter;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.model.Character;
import org.springframework.stereotype.Component;

@Component
public class CharacterDtoConverter {
    private final CharacterLocationDtoConverter characterLocationDtoConverter;

    public CharacterDtoConverter(CharacterLocationDtoConverter characterLocationDtoConverter) {
        this.characterLocationDtoConverter = characterLocationDtoConverter;
    }

    public CharacterDto characterToCharacterDto(Character from){
        return new CharacterDto(from.getName(), from.getStatus(), from.getSpecies(), from.getType(), from.getGender(),characterLocationDtoConverter.locationToCharacterLocationDto(from.getLocation()));
    }


}
