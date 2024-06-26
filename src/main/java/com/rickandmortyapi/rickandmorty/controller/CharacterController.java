package com.rickandmortyapi.rickandmorty.controller;

import com.rickandmortyapi.rickandmorty.custom.MyLogger;
import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.request.CreateCharacterRequest;
import com.rickandmortyapi.rickandmorty.request.CharacterRequest;
import com.rickandmortyapi.rickandmorty.response.BaseResponse;
import com.rickandmortyapi.rickandmorty.service.CharacterService;
import com.rickandmortyapi.rickandmorty.service.MyLoggerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CharacterController {

    private final MyLoggerService myLoggerService;
    private final CharacterService characterService;

    public CharacterController(MyLoggerService myLoggerService, CharacterService characterService) {
        this.myLoggerService = myLoggerService;
        this.characterService = characterService;
    }

    //Burda BaseResponse'a List<CharacterDto> verince artik bizim T tipimiz List<CharacterDto> oluyor.
    @GetMapping("/getAllCharacters")
    @MyLogger
    public BaseResponse<List<CharacterDto>> getAllCharacters(@RequestParam( name = "page",defaultValue = "0") int page,
                                                             @RequestParam(value = "size",defaultValue = "2") int size) { //default deger verip eger deger verilmezse bos donmemesi icin default deger verdim.
        return new BaseResponse<>(characterService.getAllCharacters(page,size));
    }
    //tek tek boyle parametrelere yazacagima bir tane ozel anotasyon yazip ordan pagination islemi yapabilir miyim? Boyle her bir endpoint icin yapmak mantikli mi?

    @PostMapping("/character")
    @MyLogger
    public ResponseEntity<CharacterDto> createCharacter(@Valid @RequestBody CreateCharacterRequest createCharacterRequest) {
        return ResponseEntity.ok(characterService.createCharacter(createCharacterRequest));
    }
    @PutMapping("/character/update/{id}")
    public BaseResponse<CharacterDto> updateCharacter(@PathVariable String id,
                                                      @RequestBody CharacterRequest characterRequest) {
        return new BaseResponse<>(characterService.updateCharacter(id, characterRequest));
    }

    @GetMapping("/characters/{id}")
    @MyLogger
    public BaseResponse<CharacterDto> getFindById(@PathVariable String id) {
        return new BaseResponse<>(characterService.getCharacterById(id));
    }
}
