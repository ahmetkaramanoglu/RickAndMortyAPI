package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.dto.LocationDto;
import com.rickandmortyapi.rickandmorty.dto.converter.LocationDtoConverter;
import com.rickandmortyapi.rickandmorty.exception.CharacterNotFoundException;
import com.rickandmortyapi.rickandmorty.exception.LocationNotFoundException;
import com.rickandmortyapi.rickandmorty.model.Character;
import com.rickandmortyapi.rickandmorty.model.Location;
import com.rickandmortyapi.rickandmorty.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LocationService {
    private final LocationDtoConverter locationDtoConverter;
    private final LocationRepository locationRepository;

    protected Location findById(String id){
        Location location = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException("404", "Location could not find by id: " + id));
        return location;
    }

    public List<LocationDto> getAllLocations(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Location> locations = locationRepository.findAll(pageable);
        return locations.stream().map(locationDtoConverter::locationToLocationDto).collect(Collectors.toList());
    }

    public LocationDto getLocationById(String id){
        Location location = findById(id);
        return locationDtoConverter.locationToLocationDto(location);
    }
//    public Set<LocationDto> getAllLocations(){
//        return locationRepository.findAll().stream().map(locationDtoConverter::locationToLocationDto).collect(Collectors.toSet());
//    }

}
