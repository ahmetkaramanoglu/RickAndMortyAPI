package com.rickandmortyapi.rickandmorty.custom;

import com.rickandmortyapi.rickandmorty.service.MyLoggerService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
public class AspectMyLogger {
    private final MyLoggerService myLoggerService;
    public AspectMyLogger(MyLoggerService myLoggerService) {
        this.myLoggerService = myLoggerService;
    }

    //Burasi MyLogger anotasyonunu yakalayip loglama islemi yapiyor
    //proceed cagrilan methodu calistirir ve geriye donen degeri return eder. Mesela getCharacterById methodu calisinca o methodu calistirir.
    @Around("@annotation(MyLogger)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        for (Object object : joinPoint.getArgs()) {
            System.out.println("List of parameters : " + object);
        }
        myLoggerService.log(joinPoint.getSignature().toShortString(), Arrays.toString(joinPoint.getArgs()));
        return proceed;
    }
}
