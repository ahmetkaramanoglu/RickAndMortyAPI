package com.rickandmortyapi.rickandmorty.controller;

import com.rickandmortyapi.rickandmorty.custom.MyLogger;
import com.rickandmortyapi.rickandmorty.dto.UserDto;
import com.rickandmortyapi.rickandmorty.request.AuthRequest;
import com.rickandmortyapi.rickandmorty.request.CreateUserRequest;
import com.rickandmortyapi.rickandmorty.request.UserLoginRequest;
import com.rickandmortyapi.rickandmorty.response.BaseResponse;
import com.rickandmortyapi.rickandmorty.service.JwtService;
import com.rickandmortyapi.rickandmorty.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    @MyLogger
    public BaseResponse<UserDto> register(@RequestBody CreateUserRequest createUserRequest) {
        return new BaseResponse<>(userService.register(createUserRequest));
    }


    //Burda if olmali mi?
    @PostMapping("/generateToken")
    @MyLogger
    public String generateToken(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()));
        if (authentication.isAuthenticated()) { //bunu servicede mi kontrol etmeliyiz acaba?
            return jwtService.generateToken(request.getUsername());
        }
        //user service'de kontrol edip eger authenticated ise userservice'de methodun icinde jwt service kullanip token mi uretmeliyiz?
        throw new UsernameNotFoundException("invalid username {} " + request.getUsername());
    }

    @PostMapping("/login")
    @MyLogger
    public BaseResponse<UserDto> login(@RequestBody UserLoginRequest userLoginRequest) {
        return new BaseResponse<>(userService.login(userLoginRequest));
    }
}
