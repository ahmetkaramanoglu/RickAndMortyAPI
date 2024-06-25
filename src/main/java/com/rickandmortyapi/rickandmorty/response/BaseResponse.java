package com.rickandmortyapi.rickandmorty.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class BaseResponse<T> {

    private Status status;
    private T results;

    public BaseResponse( T results) {
        this.status = new Status(true, null, null);
        this.results = results;
    }


}
