package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.dto.converter.CharacterDtoConverter;
import com.rickandmortyapi.rickandmorty.exception.CharacterNotFoundException;
import com.rickandmortyapi.rickandmorty.exception.GeneralNotFoundException;
import com.rickandmortyapi.rickandmorty.helper.GenericService;
import com.rickandmortyapi.rickandmorty.model.Character;
import com.rickandmortyapi.rickandmorty.repository.CharacterRepository;
import com.rickandmortyapi.rickandmorty.response.Status;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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

    public List<CharacterDto> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        return characters.stream().map(characterDtoConverter::characterToCharacterDto).collect(Collectors.toList());
    }

    public CharacterDto getCharacterById(String id){
        Character character = (Character) genericService.findById(id, characterRepository, new CharacterNotFoundException(Status.ERROR.getStatus(), "Character could not find by id : " + id));
        ///Character character = characterRepository.findById(id).orElseThrow(() -> new GeneralNotFoundException("Character could not find by id : " + id ));
        CharacterDto characterDto = characterDtoConverter.characterToCharacterDto(character);
        return characterDto;
    }
}
