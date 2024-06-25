package com.rickandmortyapi.rickandmorty.repository;

import com.rickandmortyapi.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface CharacterRepository extends JpaRepository<Character, String>{

}
