package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.dto.converter.CharacterDtoConverter;
import com.rickandmortyapi.rickandmorty.exception.GeneralNotFoundException;
import com.rickandmortyapi.rickandmorty.helper.GenericService;
import com.rickandmortyapi.rickandmorty.model.Character;
import com.rickandmortyapi.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CharacterService {
    private final GenericService genericService;
    private final CharacterRepository characterRepository;
    private final CharacterDtoConverter characterDtoConverter;

    public CharacterService(GenericService genericService, CharacterRepository characterRepository, CharacterDtoConverter characterDtoConverter) {
        this.genericService = genericService;
        this.characterRepository = characterRepository;
        this.characterDtoConverter = characterDtoConverter;
    }

    public Set<CharacterDto> getAllCharacters() {
        Set<Character> characters = new HashSet<>(characterRepository.findAll());
        Set<CharacterDto> characterDto = characters.stream().map(characterDtoConverter::characterToCharacterDto).collect(Collectors.toSet());
        return characterDto;
    }

    public CharacterDto getCharacterById(String id){
        Character character = (Character) genericService.findById(id, characterRepository,"Character");
        ///Character character = characterRepository.findById(id).orElseThrow(() -> new GeneralNotFoundException("Character could not find by id : " + id ));
        CharacterDto characterDto = characterDtoConverter.characterToCharacterDto(character);
        return characterDto;
    }
}
