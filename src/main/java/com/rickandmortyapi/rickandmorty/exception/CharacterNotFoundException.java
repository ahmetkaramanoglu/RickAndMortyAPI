package com.rickandmortyapi.rickandmorty.exception;

import com.rickandmortyapi.rickandmorty.response.Status;

public class CharacterNotFoundException extends GeneralNotFoundException {

    public CharacterNotFoundException(String status, String message) {
        super(status, message);
    }

}

