package com.rickandmortyapi.rickandmorty.repository;

import com.rickandmortyapi.rickandmorty.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
}
