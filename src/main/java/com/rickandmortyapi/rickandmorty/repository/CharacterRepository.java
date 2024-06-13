package com.rickandmortyapi.rickandmorty.repository;

import com.rickandmortyapi.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, String> {
}
