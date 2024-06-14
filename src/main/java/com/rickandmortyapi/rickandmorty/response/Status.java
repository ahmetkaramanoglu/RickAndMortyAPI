package com.rickandmortyapi.rickandmorty.response;

public enum Status {
    SUCCESS("202"),
    ERROR("404");

    private final String status;
    Status(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
