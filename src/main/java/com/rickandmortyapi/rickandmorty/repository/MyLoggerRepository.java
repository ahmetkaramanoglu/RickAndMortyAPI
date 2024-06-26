package com.rickandmortyapi.rickandmorty.repository;

import com.rickandmortyapi.rickandmorty.model.MyLoggerClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyLoggerRepository extends JpaRepository<MyLoggerClass, String> {
}
