package com.rickandmortyapi.rickandmorty.repository.custom;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.model.Character;
import com.rickandmortyapi.rickandmorty.request.CharacterRequest;
import com.rickandmortyapi.rickandmorty.request.CreateCharacterRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomCharacterRepository {

    @Query("UPDATE Character c SET c.name = :name WHERE c.id = :id")
    Character updateCharacter(@Param("id") String id, String name);

}
