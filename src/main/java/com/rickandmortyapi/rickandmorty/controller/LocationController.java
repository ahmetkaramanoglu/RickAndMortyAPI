package com.rickandmortyapi.rickandmorty.controller;

import com.rickandmortyapi.rickandmorty.dto.LocationDto;
import com.rickandmortyapi.rickandmorty.response.BaseResponse;
import com.rickandmortyapi.rickandmorty.service.LocationService;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public BaseResponse<List<LocationDto>> getAllLocations(@RequestParam(name = "page",defaultValue = "0") int page,
                                                           @RequestParam(name = "size",defaultValue = "2") int size) {
        return new BaseResponse<>(locationService.getAllLocations(page, size));
    }

    @GetMapping("/locations/{id}")
    public LocationDto getFindById(@PathVariable String id) {
        return locationService.getLocationById(id);
    }

}
