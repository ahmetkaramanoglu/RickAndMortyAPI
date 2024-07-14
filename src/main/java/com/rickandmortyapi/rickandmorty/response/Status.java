package com.rickandmortyapi.rickandmorty.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Status {
    private boolean success;
    private String errorDescription;
    private String errorCode;
}
