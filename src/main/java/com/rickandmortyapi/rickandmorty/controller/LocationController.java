package com.rickandmortyapi.rickandmorty.controller;

import com.rickandmortyapi.rickandmorty.dto.LocationDto;
import com.rickandmortyapi.rickandmorty.service.LocationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/api")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public Set<LocationDto> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/locations/{id}")
    public LocationDto getFindById(@PathVariable String id) {
        return locationService.getLocationById(id);
    }

}
