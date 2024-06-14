package com.rickandmortyapi.rickandmorty.controller;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.response.BaseResponse;
import com.rickandmortyapi.rickandmorty.response.Status;
import com.rickandmortyapi.rickandmorty.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/api")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

//    @GetMapping("/characters")
//    public ResponseEntity<Set<CharacterDto>> getAllCharacters() {
//        return ResponseEntity.ok(characterService.getAllCharacters());
//    }

    //Burda BaseResponse'a List<CharacterDto> verince artik bizim T tipimiz List<CharacterDto> oluyor.
    @GetMapping("/getAllCharacters")
    public BaseResponse<List<CharacterDto>> getAllCharacters() {
        BaseResponse<List<CharacterDto>> response = new BaseResponse<>();
        response.setResults(characterService.getAllCharacters());
        response.setStatus(Status.SUCCESS);
        return response;
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<CharacterDto> getFindById(@PathVariable String id) {
        return ResponseEntity.ok(characterService.getCharacterById(id));
    }
}
