package com.rickandmortyapi.rickandmorty.dto.converter;

import com.rickandmortyapi.rickandmorty.dto.LocationDto;
import com.rickandmortyapi.rickandmorty.model.Location;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LocationDtoConverter {
    private final CharacterDtoConverter characterDtoConverter;

    public LocationDtoConverter(CharacterDtoConverter characterDtoConverter) {
        this.characterDtoConverter = characterDtoConverter;
    }

    public LocationDto locationToLocationDto(Location from) {
        return new LocationDto(from.getName(),from.getType(),from.getDimension(), from.getResidents().stream().map(characterDtoConverter::characterToCharacterDto).collect(Collectors.toSet()));
    }
}
