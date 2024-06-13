package com.rickandmortyapi.rickandmorty.dto.converter;

import com.rickandmortyapi.rickandmorty.dto.CharacterLocationDto;
import com.rickandmortyapi.rickandmorty.model.Location;
import org.springframework.stereotype.Component;

@Component
public class CharacterLocationDtoConverter {
    public CharacterLocationDto locationToCharacterLocationDto(Location from){
        return new CharacterLocationDto(from.getName(), from.getType(), from.getDimension());
    }
}
