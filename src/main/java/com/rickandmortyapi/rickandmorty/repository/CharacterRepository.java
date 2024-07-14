package com.rickandmortyapi.rickandmorty.repository;

import com.rickandmortyapi.rickandmorty.model.Character;
import com.rickandmortyapi.rickandmorty.model.Location;
import com.rickandmortyapi.rickandmorty.model.User;
import com.rickandmortyapi.rickandmorty.repository.custom.CustomCharacterRepository;
import com.rickandmortyapi.rickandmorty.request.CharacterRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CharacterRepository extends JpaRepository<Character, String> {

    //Bu sorgu ile karakterin id'si varsa update yapar yoksa insert yapar. Bu sayede performansli bir sekilde islem yapmis oluruz. 2 kere dbye gidip gelmiyoruz.
    @Modifying //JPA'nın sorgunun bir INSERT, UPDATE veya DELETE sorgusu olduğunu bilmesini sağlar.
    @Transactional
    @Query(value = "INSERT INTO Characters (id, name, status, species, type, gender, location_id) VALUES (:id, :name, :status, :species, :type, :gender, :location_id) ON DUPLICATE KEY UPDATE  name = :name, status = :status, type = :type, gender = :gender, location_id = :location_id", nativeQuery = true)
        int insertOrUpdateCharacter(String id, String name, String status, String species, String type, String gender, String location_id);
    //location id vermemiz gerekiyor yoksa field doesnt have value hatasi aliyorum.


}
