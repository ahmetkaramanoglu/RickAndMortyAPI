package com.rickandmortyapi.rickandmorty.controller;

import com.rickandmortyapi.rickandmorty.custom.MyLogger;
import com.rickandmortyapi.rickandmorty.dto.CharacterDto;
import com.rickandmortyapi.rickandmorty.request.CreateCharacterRequest;
import com.rickandmortyapi.rickandmorty.response.BaseResponse;
import com.rickandmortyapi.rickandmorty.service.CharacterService;
import com.rickandmortyapi.rickandmorty.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/character/")
@AllArgsConstructor
public class CharacterController {
    private final CharacterService characterService;
    private final UserService userService;



    //Burda BaseResponse'a List<CharacterDto> verince artik bizim T tipimiz List<CharacterDto> oluyor.
    @GetMapping("getAllCharacters")
    @MyLogger
    public BaseResponse<List<CharacterDto>> getAllCharacters(@RequestParam(name = "page",defaultValue = "0") int page,
                                                             @RequestParam(name = "size",defaultValue = "2") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return new BaseResponse<>(characterService.getAllCharacters(page,size));
    }
    //ben jwtyi
    @PostMapping("create-character")
    @MyLogger
    public BaseResponse<CharacterDto> createCharacter(@RequestBody CreateCharacterRequest characterRequest) {
        return new BaseResponse<>(characterService.createCharacter(characterRequest));
    }

    @PutMapping("update-character/{id}")
    @MyLogger
    public BaseResponse<CharacterDto> updateCharacter(@PathVariable String id,
                                                      @RequestBody CreateCharacterRequest characterRequest) {
        return new BaseResponse<>(characterService.updateCharacter(id, characterRequest));
    }
    @DeleteMapping("delete-character/{id}")
    @MyLogger
    public BaseResponse<CharacterDto> deleteCharacter(@PathVariable String id) {
        //status durumunu inaktif yapsan daha iyi olur.
        characterService.deleteCharacter(id);
        return new BaseResponse<>(null);
    }

//    @PostMapping("/character/delete-character/{id}")
//    public ResponseEntity<CharacterDto> deleteCharacter(@PathVariable String id) {
//        characterService.deleteCharacter(id);
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("{id}")
    @MyLogger
    public BaseResponse<CharacterDto> getFindById(@PathVariable String id) {
        return new BaseResponse<>(characterService.getCharacterById(id));
    }

}
