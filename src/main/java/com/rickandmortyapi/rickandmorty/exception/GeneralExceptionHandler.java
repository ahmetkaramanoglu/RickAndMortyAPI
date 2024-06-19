package com.rickandmortyapi.rickandmorty.exception;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //bu annotation ile herhangi bir exception firlatildiginda araya giriyorsun. Ve bu classin icindeki methodlari calistiriyorsun. AOP mantigi gibi.
public class GeneralExceptionHandler {

    //MethodArgumentNotValidException hatasi firlatildiginda bu method calisir.
    @NotNull
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GeneralNotFoundException.class)
    public ResponseEntity<?> generalNotFoundExceptionHandler(GeneralNotFoundException exception) {
        //GeneralNotFountException classinin herhangi bir nesnesi firlatildiginda bu method araya girer ve exceptionu handle eder. Daha sonra status durumlari ve mesajlari ile birlikte response doner.
        ExceptionResponse exceptionResponse = new ExceptionResponse(false, exception.getErrorDescription(),exception.getErrorCode());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
}
