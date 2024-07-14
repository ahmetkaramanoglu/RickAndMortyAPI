package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.dto.LocationDto;
import com.rickandmortyapi.rickandmorty.request.CreateCharacterRequest;
import com.rickandmortyapi.rickandmorty.dto.converter.CharacterDtoConverter;
import com.rickandmortyapi.rickandmorty.dto.converter.LocationDtoToLocationConverter;
import com.rickandmortyapi.rickandmorty.exception.CharacterNotFoundException;
import com.rickandmortyapi.rickandmorty.model.Character;
import com.rickandmortyapi.rickandmorty.repository.CharacterRepository;
import com.rickandmortyapi.rickandmorty.request.CharacterRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterDtoConverter characterDtoConverter;
    private final LocationService locationService;

    public List<CharacterDto> getAllCharacters(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Character> characters = characterRepository.findAll(pageable);
        return characters.stream().map(characterDtoConverter::characterToCharacterDto).collect(Collectors.toList());
    }

    protected Character findById(String id){
        return characterRepository.findById(id).orElseThrow(
                () -> new CharacterNotFoundException("404", "Character could not find by id: " + id));
    }

    public CharacterDto getCharacterById(String id){
        return characterDtoConverter.characterToCharacterDto(findById(id));
    }

//    public CharacterDto createCharacter(CreateCharacterRequest createCharacterRequest) {
//        Character character = new Character(
//                createCharacterRequest.getName(),
//                createCharacterRequest.getStatus(),
//                createCharacterRequest.getSpecies(),
//                createCharacterRequest.getType(),
//                createCharacterRequest.getGender(),
//                locationDtoConverter.convert(createCharacterRequest.getLocation()));
//
//        return characterDtoConverter.characterToCharacterDto(characterRepository.save(character));
//    }
    public CharacterDto createCharacter(CreateCharacterRequest createCharacterRequest) {
        return insertOrUpdateCharacter(null, createCharacterRequest, createCharacterRequest.getLocationId());
    }

    protected CharacterDto insertOrUpdateCharacter(String id, CreateCharacterRequest createCharacterRequest, String locationId) {
        LocationDto locationDto = locationService.getLocationById(locationId);
        //locationId'nin locationsDto'da olup olmadigini kontrol et.

        var temp = characterRepository.insertOrUpdateCharacter(id,
                createCharacterRequest.getName(),
                createCharacterRequest.getStatus(),
                createCharacterRequest.getSpecies(),
                createCharacterRequest.getType(),
                createCharacterRequest.getGender(),
                locationId);

        //zaten etkilenen satir sayisi 1 olacak cunku ya update yapacak ya da insert yapacak.
        if(temp >= 1) {
            return CharacterDto.builder()
                    .name(createCharacterRequest.getName())
                    .status(createCharacterRequest.getStatus())
                    .species(createCharacterRequest.getSpecies())
                    .type(createCharacterRequest.getType())
                    .build();
        } else {
            throw new CharacterNotFoundException("404", "Character could not find by id: " + id);
        }
    }


    public CharacterDto updateCharacter(String id, CreateCharacterRequest createCharacterRequest) {
        return insertOrUpdateCharacter(id, createCharacterRequest, createCharacterRequest.getLocationId());
    }

    public void deleteCharacter(String id) {
        characterRepository.delete(findById(id));
    }
}
