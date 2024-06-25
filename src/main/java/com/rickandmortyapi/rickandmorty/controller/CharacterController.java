package com.rickandmortyapi.rickandmorty.controller;

import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.dto.CreateCharacterRequest;
import com.rickandmortyapi.rickandmorty.request.CharacterRequest;
import com.rickandmortyapi.rickandmorty.response.BaseResponse;
import com.rickandmortyapi.rickandmorty.response.Status;
import com.rickandmortyapi.rickandmorty.service.CharacterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse<List<CharacterDto>> getAllCharacters(@RequestParam( name = "page",defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "2") int size) { //default deger verip eger deger verilmezse bos donmemesi icin default deger verdim.
        return new BaseResponse<>(characterService.getAllCharacters(page,size));
    }
    //tek tek boyle parametrelere yazacagima bir tane ozel anotasyon yazip ordan pagination islemi yapabilir miyim? Boyle her bir endpoint icin yapmak mantikli mi?

    @PostMapping("/character")
    public ResponseEntity<CharacterDto> createCharacter(@Valid @RequestBody CreateCharacterRequest createCharacterRequest) {
        return ResponseEntity.ok(characterService.createCharacter(createCharacterRequest));
    }
    @PutMapping("/character/update/{id}")
    public BaseResponse<CharacterDto> updateCharacter(@PathVariable String id, @RequestBody CharacterRequest characterRequest) {
        return new BaseResponse<>(characterService.updateCharacter(id, characterRequest));
    }

    @GetMapping("/characters/{id}")
    public BaseResponse<CharacterDto> getFindById(@PathVariable String id) {
        return new BaseResponse<>(characterService.getCharacterById(id));
    }
}
