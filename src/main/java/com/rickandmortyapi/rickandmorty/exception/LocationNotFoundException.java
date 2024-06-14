package com.rickandmortyapi.rickandmorty.exception;

import com.rickandmortyapi.rickandmorty.response.Status;

public class LocationNotFoundException extends GeneralNotFoundException{
    public LocationNotFoundException(String status,String message) {
        super(status,message);
    }
}
