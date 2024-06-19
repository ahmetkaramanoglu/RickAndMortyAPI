package com.rickandmortyapi.rickandmorty.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status {

    private boolean success;
    private String errorDescription;
    private String errorCode;


}
