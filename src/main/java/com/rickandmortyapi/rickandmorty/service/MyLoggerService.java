package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.model.MyLoggerClass;
import com.rickandmortyapi.rickandmorty.repository.MyLoggerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class MyLoggerService {
    private  final MyLoggerRepository myLoggerRepository;
    public void log(String path,String response,String request,String username) {
        myLoggerRepository.save(new MyLoggerClass(path,response,request,username,LocalDateTime.now()));
    }
}
