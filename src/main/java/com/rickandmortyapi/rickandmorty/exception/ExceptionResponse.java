package com.rickandmortyapi.rickandmorty.exception;

import com.rickandmortyapi.rickandmorty.response.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private Status status;

    public ExceptionResponse(boolean success, String errorDescription, String errorCode) {
        this.status = new Status(success, errorDescription, errorCode);
    }
}
