package com.rickandmortyapi.rickandmorty.helper;

import com.rickandmortyapi.rickandmorty.exception.GeneralNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class GenericService<T,String> {

    //Her servicede id'ye gore bulma methodu yazmak yerine direkt olarak bunu generic yapmak istedim.
    public  T findById(String id, JpaRepository<T, String> repository, GeneralNotFoundException type) {
        return repository.findById(id).orElseThrow(() -> type);
    }
}
