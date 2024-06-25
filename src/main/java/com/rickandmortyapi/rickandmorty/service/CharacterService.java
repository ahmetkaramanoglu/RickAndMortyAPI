package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.dto.CreateCharacterRequest;
import com.rickandmortyapi.rickandmorty.dto.converter.CharacterDtoConverter;
import com.rickandmortyapi.rickandmorty.dto.converter.LocationDtoConverter;
import com.rickandmortyapi.rickandmorty.dto.converter.LocationDtoToLocationConverter;
import com.rickandmortyapi.rickandmorty.exception.CharacterNotFoundException;
import com.rickandmortyapi.rickandmorty.model.Character;
import com.rickandmortyapi.rickandmorty.repository.CharacterRepository;
import com.rickandmortyapi.rickandmorty.request.CharacterRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterDtoConverter characterDtoConverter;
    private final LocationDtoToLocationConverter converter;

    public CharacterService(CharacterRepository characterRepository, CharacterDtoConverter characterDtoConverter, LocationDtoToLocationConverter converter) {

        this.characterRepository = characterRepository;
        this.characterDtoConverter = characterDtoConverter;
        this.converter = converter;
    }

    public List<CharacterDto> getAllCharacters(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Character> characters = characterRepository.findAll(pageable);
        return characters.stream().map(characterDtoConverter::characterToCharacterDto).collect(Collectors.toList());
    }

    protected Character findById(String id){
        return characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException("404", "Character could not find by id: " + id));
    }

    public CharacterDto getCharacterById(String id){
        return characterDtoConverter.characterToCharacterDto(findById(id));
    }

    public CharacterDto createCharacter(CreateCharacterRequest createCharacterRequest) {
        Character character = new Character(createCharacterRequest.getName(), createCharacterRequest.getStatus(), createCharacterRequest.getSpecies(), createCharacterRequest.getType(), createCharacterRequest.getGender(), converter.convert(createCharacterRequest.getLocation()));
        return characterDtoConverter.characterToCharacterDto(characterRepository.save(character));
    }

    public CharacterDto updateCharacter(String id, CharacterRequest characterRequest) {
        //Eger name vermezse o bad request'i nasil yakalayacaksin?
        Character character = findById(id); //Eger id bulunamazsa exception firlatir.
        character.setName(characterRequest.getName());
        character.setStatus(characterRequest.getStatus());
        character.setSpecies(characterRequest.getSpecies());
        character.setType(characterRequest.getType());
        character.setGender(characterRequest.getGender());
        return characterDtoConverter.characterToCharacterDto(characterRepository.save(character));
    }
}
