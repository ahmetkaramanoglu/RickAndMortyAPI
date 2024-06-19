package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.dto.converter.CharacterDtoConverter;
import com.rickandmortyapi.rickandmorty.exception.CharacterNotFoundException;
import com.rickandmortyapi.rickandmorty.model.Character;
import com.rickandmortyapi.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterDtoConverter characterDtoConverter;

    public CharacterService(CharacterRepository characterRepository, CharacterDtoConverter characterDtoConverter) {

        this.characterRepository = characterRepository;
        this.characterDtoConverter = characterDtoConverter;
    }

    public List<CharacterDto> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        return characters.stream().map(characterDtoConverter::characterToCharacterDto).collect(Collectors.toList());
    }

    protected Character findById(String id){
        Character character = characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException("404", "Character could not find by id: " + id));
        return character;
    }

    public CharacterDto getCharacterById(String id){
        CharacterDto characterDto = characterDtoConverter.characterToCharacterDto(findById(id));
        return characterDto;
    }
}
