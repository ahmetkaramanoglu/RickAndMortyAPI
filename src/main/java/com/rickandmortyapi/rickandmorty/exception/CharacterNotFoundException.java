package com.rickandmortyapi.rickandmorty.exception;

import com.rickandmortyapi.rickandmorty.response.Status;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class CharacterNotFoundException extends GeneralNotFoundException {

    public CharacterNotFoundException(String errorCode, String errorDescription) {
        super(errorCode, errorDescription);
    }
}

