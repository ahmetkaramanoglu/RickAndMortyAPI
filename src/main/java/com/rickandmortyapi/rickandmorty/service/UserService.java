package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.dto.UserDto;
import com.rickandmortyapi.rickandmorty.dto.converter.UserDtoConverter;
import com.rickandmortyapi.rickandmorty.model.User;
import com.rickandmortyapi.rickandmorty.repository.UserRepository;
import com.rickandmortyapi.rickandmorty.request.CreateUserRequest;
import com.rickandmortyapi.rickandmorty.request.UserLoginRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final PasswordEncoder passwordEncoder;
    public UserDto register(CreateUserRequest createUserRequest) {
        User user = new User(createUserRequest.getUsername(), passwordEncoder.encode(createUserRequest.getPassword()));
       return userDtoConverter.convertToUserDto(userRepository.save(user));
    }
    protected Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = getUserByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }

    public UserDto login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByUsername(userLoginRequest.getUsername()).orElseThrow(EntityNotFoundException::new);
        if(passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            return userDtoConverter.convertToUserDto(user);
        }
        else{
            throw new EntityNotFoundException();
        }
    }
}
