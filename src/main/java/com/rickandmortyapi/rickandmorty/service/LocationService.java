package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.dto.LocationDto;
import com.rickandmortyapi.rickandmorty.dto.converter.LocationDtoConverter;
import com.rickandmortyapi.rickandmorty.helper.GenericService;
import com.rickandmortyapi.rickandmorty.model.Location;
import com.rickandmortyapi.rickandmorty.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final GenericService genericService;
    private final LocationDtoConverter locationDtoConverter;
    private final LocationRepository locationRepository;

    public LocationService(GenericService genericService, LocationDtoConverter locationDtoConverter, LocationRepository locationRepository) {
        this.genericService = genericService;
        this.locationDtoConverter = locationDtoConverter;
        this.locationRepository = locationRepository;
    }



    public LocationDto getLocationById(String id){
        Location location = (Location) genericService.findById(id, locationRepository,"Location");
        return locationDtoConverter.locationToLocationDto(location);
    }
    public Set<LocationDto> getAllLocations(){
        return locationRepository.findAll().stream().map(locationDtoConverter::locationToLocationDto).collect(Collectors.toSet());
    }

}
