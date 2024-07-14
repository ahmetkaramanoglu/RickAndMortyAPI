package com.rickandmortyapi.rickandmorty.dto.converter;

import com.rickandmortyapi.rickandmorty.dto.UserDto;
import com.rickandmortyapi.rickandmorty.model.User;
import com.rickandmortyapi.rickandmorty.request.CreateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserDto convertToUserDto(User from) {
        return new UserDto(from.getUsername(), from.getPassword());
    }
}
