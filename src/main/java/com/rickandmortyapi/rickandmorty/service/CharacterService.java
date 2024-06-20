package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.dto.CreateCharacterRequest;
import com.rickandmortyapi.rickandmorty.dto.converter.CharacterDtoConverter;
import com.rickandmortyapi.rickandmorty.dto.converter.LocationDtoConverter;
import com.rickandmortyapi.rickandmorty.dto.converter.LocationDtoToLocationConverter;
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
    private final LocationDtoToLocationConverter converter;

    public CharacterService(CharacterRepository characterRepository, CharacterDtoConverter characterDtoConverter, LocationDtoToLocationConverter converter) {

        this.characterRepository = characterRepository;
        this.characterDtoConverter = characterDtoConverter;
        this.converter = converter;
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

    public CharacterDto createCharacter(CreateCharacterRequest createCharacterRequest) {
        Character character = new Character(createCharacterRequest.getName(), createCharacterRequest.getStatus(), createCharacterRequest.getSpecies(), createCharacterRequest.getType(), createCharacterRequest.getGender(), converter.convert(createCharacterRequest.getLocation()));
        return characterDtoConverter.characterToCharacterDto(characterRepository.save(character));
    }
}
