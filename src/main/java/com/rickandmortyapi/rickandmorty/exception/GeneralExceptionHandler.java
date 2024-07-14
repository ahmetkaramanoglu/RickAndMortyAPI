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

import io.jsonwebtoken.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice //bu annotation ile herhangi bir exception firlatildiginda araya giriyorsun. Ve bu classin icindeki methodlari calistiriyorsun. AOP mantigi gibi.
public class GeneralExceptionHandler {

    //MethodArgumentNotValidException hatasi firlatildiginda bu method calisir.
    @NotNull
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        //ExceptionResponse exceptionResponse = new ExceptionResponse(false, "Validation Error", "400");

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> generalExceptionHandler(){
        ExceptionResponse exceptionResponse = new ExceptionResponse(false, "Beklenmedik bir hata olustu.","500");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> signatureExceptionHandler(){
        ExceptionResponse exceptionResponse = new ExceptionResponse(false, "Token gecerli degil.","403");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);

    }

    //exception handler controlleri dinliyor. servlet seviyesinde dinleyenleri arastir.

    @ExceptionHandler(GeneralNotFoundException.class)
    public ResponseEntity<?> generalNotFoundExceptionHandler(GeneralNotFoundException exception) {
        //GeneralNotFountException classinin herhangi bir nesnesi firlatildiginda bu method araya girer ve exceptionu handle eder. Daha sonra status durumlari ve mesajlari ile birlikte response doner.
        ExceptionResponse exceptionResponse = new ExceptionResponse(false, exception.getErrorDescription(),exception.getErrorCode());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }
}
