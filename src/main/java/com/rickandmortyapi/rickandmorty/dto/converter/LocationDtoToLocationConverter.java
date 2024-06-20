package com.rickandmortyapi.rickandmorty.dto.converter;

import com.rickandmortyapi.rickandmorty.dto.CharacterLocationDto;
import com.rickandmortyapi.rickandmorty.dto.LocationDto;
import com.rickandmortyapi.rickandmorty.model.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoToLocationConverter {
    public Location convert(CharacterLocationDto from){
        return new Location(from.getName(), from.getType(), from.getDimension());
    }
}
