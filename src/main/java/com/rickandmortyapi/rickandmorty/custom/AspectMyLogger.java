package com.rickandmortyapi.rickandmorty.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rickandmortyapi.rickandmorty.service.MyLoggerService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Aspect
@Component
@AllArgsConstructor
public class AspectMyLogger {
    private final MyLoggerService myLoggerService;
    private final ObjectMapper objectMapper;

    //Burasi MyLogger anotasyonunu yakalayip loglama islemi yapiyor
    //proceed cagrilan methodu calistirir ve geriye donen degeri return eder. Mesela getCharacterById methodu calisinca o methodu calistirir.
    @Around("@annotation(MyLogger)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Set<String> myRequest = new HashSet<>();
        Set<String> myResponse = new HashSet<>();
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.out.println("Username : " + SecurityContextHolder.getContext().getAuthentication().getName());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //burda joinPoint.getArgs yapinca aslinda parametreleri tekrar calistiriyor gibi. Yani parametrelere verilen degerleri getiriyor.
        for (Object object : joinPoint.getArgs()) {
            try{
                String value = objectMapper.writeValueAsString(object);
                myRequest.add(value);
                System.out.println("Request : " + value);
            }catch (Exception e){
                System.out.println(e);
            }
        }
        //burda direkt olarak jsonresponse donunce ne oluyor bi bak
        //Aslinda burdaki proceed donus degerini yakalamak gibi dusun. Yani new BaseResponse... yapiyorsun ya. Onu aslinda burda joinPoint.proceed() ile yapiyorsun ve proceed aslinda BaseResponse tipinden icinde result olan degerleri almis oluyorsun.
        Object proceed = joinPoint.proceed();
        try {
            String jsonResponse = objectMapper.writeValueAsString(proceed);
            myResponse.add(jsonResponse);
            System.out.println("Response: " + jsonResponse);
        } catch (Exception e) {
            System.out.println(e);
        }
        myLoggerService.log(joinPoint.getSignature().getName(),myResponse.toString(), username,myRequest.toString());
        return proceed;
    }
}
