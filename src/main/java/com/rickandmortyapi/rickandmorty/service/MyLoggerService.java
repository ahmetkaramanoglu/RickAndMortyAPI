package com.rickandmortyapi.rickandmorty.service;

import com.rickandmortyapi.rickandmorty.model.MyLoggerClass;
import com.rickandmortyapi.rickandmorty.repository.MyLoggerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MyLoggerService {
    private  final MyLoggerRepository myLoggerRepository;
    public MyLoggerService(MyLoggerRepository myLoggerRepository) {
        this.myLoggerRepository = myLoggerRepository;
    }

    public void log(String path,String data) {
        myLoggerRepository.save(new MyLoggerClass(path,data ,LocalDateTime.now()));
    }
}
