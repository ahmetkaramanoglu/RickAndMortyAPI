package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.dto.LocationDto;
import com.rickandmortyapi.rickandmorty.dto.converter.LocationDtoConverter;
import com.rickandmortyapi.rickandmorty.exception.CharacterNotFoundException;
import com.rickandmortyapi.rickandmorty.exception.LocationNotFoundException;
import com.rickandmortyapi.rickandmorty.model.Character;
import com.rickandmortyapi.rickandmorty.model.Location;
import com.rickandmortyapi.rickandmorty.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {


    private final LocationDtoConverter locationDtoConverter;
    private final LocationRepository locationRepository;

    public LocationService(LocationDtoConverter locationDtoConverter, LocationRepository locationRepository) {

        this.locationDtoConverter = locationDtoConverter;
        this.locationRepository = locationRepository;
    }


    protected Location findById(String id){
        Location location = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException("404", "Location could not find by id: " + id));
        return location;
    }

    public LocationDto getLocationById(String id){
        Location location = findById(id);
        return locationDtoConverter.locationToLocationDto(location);
    }
    public Set<LocationDto> getAllLocations(){
        return locationRepository.findAll().stream().map(locationDtoConverter::locationToLocationDto).collect(Collectors.toSet());
    }

}
